package ecard.biz.excel;
import jex.data.JexData;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jex.data.JexData;
import jex.data.JexDataList;
import jex.exception.JexBIZException;
import jex.log.JexLogFactory;
import jex.log.JexLogger;
import jex.resource.cci.JexConnection;
import jex.resource.cci.JexConnectionManager;
import jex.sys.JexSystem;
import jex.util.date.DateTime;
import jex.util.DomainUtil;
import jex.util.StringUtil;
import jex.util.biz.JexCommonUtil;
import jex.JexContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bizplay.data.export.Export;
import com.bizplay.data.export.ExportConstants;
import com.bizplay.data.export.ExportFactory;
import com.bizplay.data.export.data.handler.RowHandlerImpl;
import com.bizplay.data.export.form.Form;
import com.bizplay.data.export.form.FormParser;
import com.bizplay.data.export.form.attribute.Column;
import com.bizplay.data.export.form.attribute.Message;
import com.bizplay.data.export.form.attribute.Row;
import com.bizplay.data.export.mapper.AbstractMapper;

import ecard.cmo.ECARD_CMO;

public class DownloadManager {
	
	private static final JexLogger LOG = JexLogFactory.getLogger(DownloadManager.class);
	public static final String 	ROW_HANDLE_CLS   = "erp2.row.handler.RowHandlerRcpt";
	public static final String 	DATA_ID_PREFIX   = "ecard.biz.excel.data";
	public static final String 	BASIC_FORM       = "00";
	public static final String	EXECUTION 		 = "0";
	public static final String	DATA_QUERY		 = "1";
	public static final String	EXCEL_GENERATION = "2";
	public static final String	WRITE_ERP_FILE	 = "3";
	public static final String	DONE			 = "4";
	public static final String	FAIL			 = "5";
	public static final String	SUCCESS			 = "6";

	private long 	 	beginTime     = 0L;
	private long  		lastEventTime = 0L;
	private String     token         = null;
	private ECARD_CMO  sessionData   = null;
	private JSONObject param         = null;
	private String     fileDir       = null;
	private String     filePath      = null;
	private String     fileExt       = null;
	
	/**
	 * 
	 * @param param
	 * @param sessionData
	 */
	public DownloadManager(JSONObject param , ECARD_CMO sessionData){
		this.token       = DateTime.getInstance().getSysDate("YYYYMMDD").concat("-").concat(UUID.randomUUID().toString());
		this.param       = param;
		this.sessionData = sessionData;
		this.beginTime   = System.currentTimeMillis();
		
		if( "Real".equals(StringUtil.null2void(System.getProperty("IS_REAL"))) ){
			this.fileDir = "/CEDA/webank_filedata/excel_tmp/";
			
			//이행계 서버 관련 처리
			if(!(new File(this.fileDir)).isDirectory() ){
				this.fileDir = System.getProperty("java.io.tmpdir").concat("/");	
			}
			
		}else{
			this.fileDir = System.getProperty("java.io.tmpdir").concat("/");
		}
		
		changeStatus(EXECUTION);
	}

	
	/**
	 * 다운로드를 비동기로 실행합니다.
	 */
	public void execute(){
        Runnable task = new Runnable() {
        	@Override
        	public void run() { 
                try {
                	download();
                }catch (Exception e){
                	e.printStackTrace();
                	changeStatus(FAIL);
                }
            }
        };
        new Thread(task, "ExcelDownloadThread").start(); 
    }	
	
	
	//public void execute(final JSONObject param , final ECARD_CMO sessionData){
    //    Runnable task = new Runnable() {
    //    	@Override
    //    	public void run() { 
    //            try {
    //            	download(param , sessionData);
    //            }catch (Exception e){
    //            	changeStatus(STATUS_FAIL);
    //            }
    //        }
    //    };
    //    new Thread(task, "ExcelDownloadThread").start(); 
    //}

	
	/**
	 * Token값을 리턴합니다.
	 */
	public String getToken(){
		return this.token;
	}

	
	/**
	 * 다운로드를 실행합니다.
	 * @throws Exception
	 */
	private void download() throws Exception{
		/* = -------------------------------------------------------------------------- = */
		String            strDataID    = StringUtil.null2void((String)param.get("DATA_ID"    ) , "" );
		String            strErpDocNo  = StringUtil.null2void((String)param.get("ERP_DOC_NO" ) , "" );
		String            strTitle     = StringUtil.null2void((String)param.get("TITLE"      ) , "" );
		String            strMessage   = StringUtil.null2void((String)param.get("MESSAGE"    ) , "" );
		JSONArray         strHead      = (JSONArray)param.get("HEAD");
		JSONArray         strBody      = (JSONArray)param.get("BODY");
		/* = -------------------------------------------------------------------------- = */
		AbstractExcelData     excelData = null;
		Map <String , Object> dataInfo  = new HashMap<String , Object>();
		Map <Object , Object> headData  = null;
		JexDataList		      bodyData  = null;
		/* = -------------------------------------------------------------------------- = */
		Form				  mainForm  = null;
		Form				  subForm1  = null;
		Export 				  export    = ExportFactory.getDefaultExport();
		/* = -------------------------------------------------------------------------- = */
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  Excel Data 조회															= */
		/* = -------------------------------------------------------------------------- = */
		excelData = (AbstractExcelData)Class.forName(DATA_ID_PREFIX.concat(".").concat(strDataID)).newInstance();
		dataInfo  = excelData.execute(param , sessionData);
		

		/* = -------------------------------------------------------------------------- = */
		/* =  파일 상태 변경																= */
		/* = -------------------------------------------------------------------------- = */		
		changeStatus(DATA_QUERY);
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  헤더 데이터 저장																= */
		/* = -------------------------------------------------------------------------- = */		
		if( dataInfo.get("HEAD_DATA") != null ){
			headData = (Map<Object , Object>)dataInfo.get("HEAD_DATA"); 
		}

		
		/* = -------------------------------------------------------------------------- = */
		/* =  바디 데이터 저장																= */
		/* = -------------------------------------------------------------------------- = */		
		if( dataInfo.get("BODY_DATA") != null ){
			bodyData = (JexDataList)dataInfo.get("BODY_DATA"); 
		}
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  다운로드 양식 생성															= */
		/* = -------------------------------------------------------------------------- = */
		if(BASIC_FORM.equals(strErpDocNo)){
			//비즈플레이 기본양식
			mainForm = makeBasicForm(strTitle , strMessage , strHead , strBody);
		}else{
			//ERP 양식
			String strFormXml = (getFormInfo(strErpDocNo)).getString("EXP_XML");
			String strAddDoc1 = (getFormInfo(strErpDocNo)).getString("ADD_DOC1");

			//Main Form 생성
			mainForm = FormParser.parse(strFormXml);
			if(mainForm.getTitle() != null){
				mainForm.setTitle(strTitle);
			}

			//Sub Form 생성
			if( !"".equals(StringUtil.null2void(strAddDoc1)) ){
				strFormXml = (getFormInfo(strAddDoc1)).getString("EXP_XML");
				subForm1   = FormParser.parse(strFormXml);
			}
		}
		
		/* = -------------------------------------------------------------------------- = */
		/* =  Main Form 출력 데이터 Set													= */
		/* = -------------------------------------------------------------------------- = */		
		mainForm.setHeadData(headData);
		mainForm.setBodyData(bodyData);
		export.add(mainForm);
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  Sub Form 출력 데이터 Set													= */
		/* = -------------------------------------------------------------------------- = */		
		if(subForm1 != null){
			bodyData.beforeFirst();
			subForm1.setHeadData(headData);
			subForm1.setBodyData(bodyData);
			export.add(subForm1);
		}
		

		/* = -------------------------------------------------------------------------- = */
		/* =  Data Export																= */
		/* = -------------------------------------------------------------------------- = */
		export.export();
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  파일 상태 변경																= */
		/* = -------------------------------------------------------------------------- = */		
		changeStatus(EXCEL_GENERATION);
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  파일 저장																	= */
		/* = -------------------------------------------------------------------------- = */		
		export.write(this.fileDir, this.token);
		

		/* = -------------------------------------------------------------------------- = */
		/* =  파일 상태 변경																= */
		/* = -------------------------------------------------------------------------- = */
		changeStatus(WRITE_ERP_FILE);		
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  파일 메타 저장																= */
		/* = -------------------------------------------------------------------------- = */
		this.filePath = this.fileDir.concat(this.token).concat(".").concat(export.getFileExtension());
		this.fileExt  = export.getFileExtension();
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  파일 상태 변경																= */
		/* = -------------------------------------------------------------------------- = */		
		changeStatus(SUCCESS);
    }
	

	/**
	 * 파일의 상태 변경 및 히스토리를 생성합니다.
	 * @param status
	 * @throws UnknownHostException 
	 */
	private void changeStatus(String status){
	    JexCommonUtil   util         = JexContext.getContext().getCommonUtil();
		JexConnection 	idoCon 		 = JexConnectionManager.createIDOConnection();
		JexData         jexData      = null;
		String 			strUseInttId = StringUtil.null2void( sessionData.getUseInttId()        , "" );
		String 			strUserId    = StringUtil.null2void( sessionData.getUserId   ()        , "" );
		String 			strUserNo    = StringUtil.null2void( sessionData.getUserNo   ()        , "" );
		String 			strReqPage   = StringUtil.null2void( (String)param.get("PAGE_URL_ADR") , "" );
		String 			strDocNo     = StringUtil.null2void( (String)param.get("ERP_DOC_NO"  ) , "" );
		String 			strTitle     = StringUtil.null2void( (String)param.get("TITLE"       ) , "" );
		String 			strParam     = param.toJSONString();
		Long   			lCurrentTime = System.currentTimeMillis();
		
		try{
			
			//히스토리 정보 생성
			if(EXECUTION.equals(status)){
				JexData idoIn1  =  util.createIDOData("EXCEL_DOWN_HIST_C001");
				idoIn1.put("FILE_TOKEN",  this.token  	);
				idoIn1.put("FILE_NM",  strTitle    	);
				idoIn1.put("FILE_STS",  status      	);
				idoIn1.put("USE_INTT_ID",  strUseInttId	);
				idoIn1.put("USER_ID",  strUserId   	);
				idoIn1.put("USER_NO",  strUserNo   	);
				idoIn1.put("PARAM",  strParam    	);
				idoIn1.put("EXE_PAGE",  strReqPage  	);
				idoIn1.put("USER_ID",  strUserId	 	);
				idoIn1.put("USE_INTT_ID",  strUseInttId	);
				idoIn1.put("EXCEL_GB",  strDocNo      );
				idoIn1.put("EXE_SVR_IP",  InetAddress.getLocalHost().getHostAddress() );
				jexData = idoCon.execute(idoIn1);
			}else if(DATA_QUERY.equals(status)){
				JexData idoIn1  =  util.createIDOData("EXCEL_DOWN_HIST_U003");
				idoIn1.put("FILE_TOKEN",  this.token	);
				idoIn1.put("FILE_STS",  status		);
				idoIn1.put("SQL_TIME",  Long.toString( lCurrentTime - this.lastEventTime ) );
				jexData = idoCon.execute(idoIn1);
			}else if(EXCEL_GENERATION.equals(status)){
				JexData idoIn1  =  util.createIDOData("EXCEL_DOWN_HIST_U004");
				idoIn1.put("FILE_TOKEN",  this.token	);
				idoIn1.put("FILE_STS",  status      );
				idoIn1.put("EXCEL_GEN_TIME",  Long.toString( lCurrentTime - this.lastEventTime ) );
				jexData = idoCon.execute(idoIn1);
			}else if(WRITE_ERP_FILE.equals(status)){	
				JexData idoIn1  =  util.createIDOData("EXCEL_DOWN_HIST_U005");
				idoIn1.put("FILE_TOKEN",  this.token	);
				idoIn1.put("FILE_STS",  status     );
				idoIn1.put("WRITE_FILE_TIME",  Long.toString( lCurrentTime - this.lastEventTime ) );
				jexData = idoCon.execute(idoIn1);
			}else if(SUCCESS.equals(status)){
				JexData idoIn1  =  util.createIDOData("EXCEL_DOWN_HIST_U001");
				idoIn1.put("FILE_TOKEN",  this.token  	);
				idoIn1.put("FILE_STS",  status        );
				idoIn1.put("FILE_EXT",  this.fileExt 	);
				idoIn1.put("FILE_PATH",  this.filePath	);
				idoIn1.put("RUN_TIME",  Long.toString( lCurrentTime - this.beginTime ) );
				jexData = idoCon.execute(idoIn1);
			}else{
				JexData  idoIn1  =  util.createIDOData("EXCEL_DOWN_HIST_U002");
				idoIn1.put("FILE_TOKEN", this.token	);
				idoIn1.put("FILE_STS", status      );
				jexData = idoCon.execute(idoIn1);
			}
			if(jexData != null){
				if (DomainUtil.isError(jexData)) {
					if (LOG.isDebug()) LOG.debug("Error Code   ::"+DomainUtil.getErrorCode	(jexData));
					if (LOG.isDebug()) LOG.debug("Error Message::"+DomainUtil.getErrorMessage(jexData));
				}				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {idoCon.close();} catch (IOException e) {e.printStackTrace();}
		}
		
		this.lastEventTime = System.currentTimeMillis();
	}


	/**
	 * Erp 엑셀 양식정보를 조회 합니다. 
	 * @param erpDocNo
	 * @return
	 * @throws Exception
	 */
	private JexData getFormInfo(String erpDocNo) throws Exception{
	    JexCommonUtil       util   = JexContext.getContext().getCommonUtil();
		JexConnection       idoCon = JexConnectionManager.createIDOConnection();
		JexData  idoIn  = null;
		JexData idoOut = null;

		try{
			idoIn  = util.createIDOData("EXCEL_FORM_R003");
			idoIn.put("EXCEL_GB", erpDocNo);
			idoOut = idoCon.execute(idoIn);
			if (DomainUtil.isError(idoOut)) {
				if (LOG.isDebug()) LOG.debug("Error Code   ::"+DomainUtil.getErrorCode   (idoOut));
				if (LOG.isDebug()) LOG.debug("Error Message::"+DomainUtil.getErrorMessage(idoOut));
				throw new JexBIZException(idoOut);
			}			
		}catch(JexBIZException e){
			throw e;
		}finally{
			idoCon.close();
		}
		
		return idoOut;
	}
	

	/**
	 * 기본 양식에 대한 Form을 생성합니다.
	 * @param title
	 * @param msg
	 * @param head
	 * @param body
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private Form makeBasicForm(String title , String msg , JSONArray head , JSONArray body) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Form           form          = new Form();
		Message        message       = new Message();
		Row            headRow       = new Row();
		Row            bodyRow       = new Row();
		Column         col           = null;
		JSONObject     itemTmp       = null;
		AbstractMapper mapperCls  = (AbstractMapper)Class.forName(ExportConstants.MAPPER_CLASS_XLS).newInstance();
		RowHandlerImpl handlerCls = (RowHandlerImpl)Class.forName(ROW_HANDLE_CLS                  ).newInstance(); 

		
		/* = -------------------------------------------------------------------------- = */
		/* =  메세지 셋팅																	= */
		/* = -------------------------------------------------------------------------- = */		
		message.setMessage(msg);
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  헤더 셋팅																	= */
		/* = -------------------------------------------------------------------------- = */
		for(int i = 0; i < head.size(); i++){
			col     = new Column();
			itemTmp = (JSONObject)head.get(i); 
			col.setValue( (String)itemTmp.get("COL") );
			headRow.setCols(col);
		}
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  바디 셋팅																	= */
		/* = -------------------------------------------------------------------------- = */		
		for(int i = 0; i < body.size(); i++){
			col     = new Column();
			itemTmp = (JSONObject)body.get(i); 
			col.setValue   ( (String)itemTmp.get("COL") );
			if(!"".equals(StringUtil.null2void((String)itemTmp.get("FN"),""))){
				col.setFunction( (String)itemTmp.get("FN" ) );	
			}
			bodyRow.setCols(col);
		}		
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  Row 핸들러 셋팅																= */
		/* = -------------------------------------------------------------------------- = */
		handlerCls.setClassName( ROW_HANDLE_CLS );
		handlerCls.setId       ( "RCPT" 		);
		handlerCls.setResult   ( "map"  		);
		
		
		/* = -------------------------------------------------------------------------- = */
		/* =  Form 생성																	= */
		/* = -------------------------------------------------------------------------- = */		
		form.setFormName  	     ( title       			);
		form.setExportType	     ( ExportConstants.XLS	);
		form.setWithPdf   	     ( false              	);
		form.setMapperClass	     ( mapperCls           	);
		form.setRowHandler       ( handlerCls			);
		form.setRowHandlerPostion("1"                   );
		form.setTitle     	     ( title      			);
		form.setMessages	     ( message      		);
		form.setHeadRows	     ( headRow      		);
		form.setBodyRows	     ( bodyRow      		);
		
		
		
		return form;
	}
	
	
	/**
	 * 파일 처리 상태를 리턴합니다. 
	 * @param token
	 * @return
	 */
	public static String getStatus(String token){
	    JexCommonUtil util    = JexContext.getContext().getCommonUtil();
		JexConnection idoCon  = JexConnectionManager.createIDOConnection();
		String        strStts = "";
		try{
			
			JexData  idoIn1  =  util.createIDOData("EXCEL_DOWN_HIST_R001");
			idoIn1.put("FILE_TOKEN", token);
			JexData idoOut1 = idoCon.execute(idoIn1);
			if (DomainUtil.isError(idoOut1)) {
				if (LOG.isDebug()) LOG.debug("Error Code   ::"+DomainUtil.getErrorCode	(idoOut1));
				if (LOG.isDebug()) LOG.debug("Error Message::"+DomainUtil.getErrorMessage(idoOut1));
				throw new JexBIZException(idoOut1);
			}
			
			strStts = idoOut1.getString("FILE_STS");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {idoCon.close();} catch (IOException e) {e.printStackTrace();}
		}

		return strStts;
	}
	
	
	/**
	 * 파일 정보를 리턴합니다.
	 * @param token
	 * @return
	 */
	public static Map<String,String> getFile(String token){
	    JexCommonUtil      util      = JexContext.getContext().getCommonUtil();
		JexConnection      idoCon    = JexConnectionManager.createIDOConnection();
		Map<String,String> mFileMeta = new HashMap<String,String>();
		try{
			
			JexData  idoIn1  =  util.createIDOData("EXCEL_DOWN_HIST_R001");
			idoIn1.put("FILE_TOKEN", token);
			JexData idoOut1 = idoCon.execute(idoIn1);
			if (DomainUtil.isError(idoOut1)) {
				if (LOG.isDebug()) LOG.debug("Error Code   ::"+DomainUtil.getErrorCode	(idoOut1));
				if (LOG.isDebug()) LOG.debug("Error Message::"+DomainUtil.getErrorMessage(idoOut1));
				throw new JexBIZException(idoOut1);
			}
			
			mFileMeta.put("STTS"       , idoOut1.getString("FILE_STS"));
			mFileMeta.put("FILE_NM"    , idoOut1.getString("FILE_NM"));
			mFileMeta.put("FILE_EXT"   , idoOut1.getString("FILE_EXT"));
			mFileMeta.put("FILE_PATH"  , idoOut1.getString("FILE_PATH"));
			mFileMeta.put("EXE_SVR_IP" , idoOut1.getString("EXE_SVR_IP"));
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {idoCon.close();} catch (IOException e) {e.printStackTrace();}
		}

		return mFileMeta;
	}
	
	
	/**
	 * 파일 다운로드 종료 상태로 변경합니다.
	 * @param token
	 */
	public static void downloadDone(String token){
	    JexCommonUtil util    = JexContext.getContext().getCommonUtil();
		JexConnection idoCon  = JexConnectionManager.createIDOConnection();
		try{
			JexData  idoIn1  =  util.createIDOData("EXCEL_DOWN_HIST_U002");
			idoIn1.put("FILE_TOKEN", token               );
			idoIn1.put("FILE_STS", DownloadManager.DONE);
			JexData idoOut1 = idoCon.execute(idoIn1);
			if (DomainUtil.isError(idoOut1)) {
				if (LOG.isDebug()) LOG.debug("Error Code   ::"+DomainUtil.getErrorCode	(idoOut1));
				if (LOG.isDebug()) LOG.debug("Error Message::"+DomainUtil.getErrorMessage(idoOut1));
				throw new JexBIZException(idoOut1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {idoCon.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	
	public static void main(String[] args) throws Exception {}
	
}
