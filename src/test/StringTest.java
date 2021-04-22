package test;

public class StringTest {
	public static void main(String[] args) {
		StringBuffer dQuery3 = new StringBuffer("");
	      
	    //String orderItem = input.getString("STRING");
	    String orderItem = "CAR_NO";
	    
	    dQuery3.append(" \n SELECT row_number() over(ORDER BY car."+orderItem+") ROW_NUM,  car.CAR_NO, car.CNRC_TYPE, car.CNRC_BIZ_NM, car.RENT_AMT, car.CAR_TYPE, car.USE_STT_DT, car.USE_END_DT, car.DEC_YN, car.ASSIGN_TYPE ");
	    dQuery3.append(" \n , case when c.Tcnt > 1 then b.GAS_CARD_NO || ' ¿Ü ' || c.Tcnt-1  else b.GAS_CARD_NO end as GAS_CARD_NO_DISP ");                          
	    dQuery3.append(" \n , c.Tcnt as GAS_CARD_NO_CNT  ");
	    dQuery3.append(" \n , case when e.Tcnt > 1 then d.USER_NM || ' ¿Ü ' || e.Tcnt-1 else d.USER_NM end as USER_NM_DISP ");
	    dQuery3.append(" \n , e.Tcnt as USER_NM_CNT, car.USE_YN ");
	    dQuery3.append(" \n FROM CARMN_BSC car ");
	    dQuery3.append(" \n left join  (  ");
	   	//dQuery3.append(" \n  select CAR_NO, GAS_CARD_NO, row_number() OVER (PARTITION BY CAR_NO) as rnum  ");
	   	dQuery3.append(" \n  select CAR_NO, substring(GAS_CARD_NO from 1 for 4)||'-'||substring(GAS_CARD_NO from 5 for 4)||'-'||substring(GAS_CARD_NO from 9 for 4)||'-'||substring(GAS_CARD_NO from 13 for 4) GAS_CARD_NO, row_number() OVER (PARTITION BY CAR_NO) as rnum  ");
	   	dQuery3.append(" \n  from CARMN_CARD_MAP  ");
	   	dQuery3.append(" \n  where 1=1 ");
//	   	dQuery3.append(" \n  AND BIZ_NO='"+carmn_Session.getString("BIZ_NO")+"'" );
//	   	dQuery3.append(" \n  AND PTL_ID='"+carmn_Session.getString("PTL_ID")+"'" );
//	   	dQuery3.append(" \n  AND USE_INTT_ID ='"+carmn_Session.getString("USE_INTT_ID")+"'" );
		dQuery3.append(" \n ) b on car.CAR_NO = b.CAR_NO and b.rnum = 1 ");
	   	
		dQuery3.append(" \n left join (  ");
		dQuery3.append(" \n  select CAR_NO, COUNT(*) Tcnt ");
		dQuery3.append(" \n   from CARMN_CARD_MAP  ");
		dQuery3.append(" \n   where 1=1 ");
//		dQuery3.append(" \n   AND BIZ_NO='"+carmn_Session.getString("BIZ_NO")+"'" );
//		dQuery3.append(" \n   AND PTL_ID='"+carmn_Session.getString("PTL_ID")+"'" );
//		dQuery3.append(" \n   AND USE_INTT_ID ='"+carmn_Session.getString("USE_INTT_ID")+"'" );
		dQuery3.append(" \n   group by CAR_NO  ");
		dQuery3.append(" \n  ) c on car.CAR_NO = c.CAR_NO  ");
		
		dQuery3.append(" \n left join (  ");
		dQuery3.append(" \n   select cum.CAR_NO, carUser.user_nm, row_number() OVER (PARTITION BY cum.CAR_NO) as rnum  ");
		dQuery3.append(" \n   from CARMN_USER_MAP cum ");
		dQuery3.append(" \n      left join CARMN_USER carUser on cum.ptl_id      = carUser.ptl_id  ");
		dQuery3.append(" \n                                  and cum.use_intt_id = carUser.use_intt_id ");
		dQuery3.append(" \n                                  and cum.BIZ_NO      = carUser.BIZ_NO  ");
		dQuery3.append(" \n                                  and cum.USER_NO     = carUser.USER_NO  ");
		
		dQuery3.append(" \n   where 1=1   ");
//		dQuery3.append(" \n   AND cum.BIZ_NO='"+carmn_Session.getString("BIZ_NO")+"'" );
//		dQuery3.append(" \n   AND cum.PTL_ID='"+carmn_Session.getString("PTL_ID")+"'" );
//		dQuery3.append(" \n   AND cum.USE_INTT_ID ='"+carmn_Session.getString("USE_INTT_ID")+"'" );
		dQuery3.append(" \n ) d on car.CAR_NO = d.CAR_NO and d.rnum = 1  ");
	  
		dQuery3.append(" \n  left join  (  ");
		dQuery3.append(" \n    select cum.CAR_NO, COUNT(*) Tcnt  ");
		dQuery3.append(" \n    from CARMN_USER_MAP cum     ");
		dQuery3.append(" \n      left join CARMN_USER carUser on cum.ptl_id      = carUser.ptl_id  ");
		dQuery3.append(" \n                                  and cum.use_intt_id = carUser.use_intt_id  ");
		dQuery3.append(" \n                                  and cum.BIZ_NO      = carUser.BIZ_NO  ");
		dQuery3.append(" \n                                  and cum.USER_NO     = carUser.USER_NO  ");
		dQuery3.append(" \n    where 1=1  ");
//		dQuery3.append(" \n   AND cum.BIZ_NO='"+carmn_Session.getString("BIZ_NO")+"'" );
//		dQuery3.append(" \n   AND cum.PTL_ID='"+carmn_Session.getString("PTL_ID")+"'" );
//		dQuery3.append(" \n   AND cum.USE_INTT_ID ='"+carmn_Session.getString("USE_INTT_ID")+"'" );
//		dQuery3.append(" \n   group by cum.CAR_NO  ");
//		dQuery3.append(" \n  ) e on car.CAR_NO = e.CAR_NO  ");
//		dQuery3.append(" \n   where 1=1  ");
//		dQuery3.append(" \n   AND car.BIZ_NO='"+carmn_Session.getString("BIZ_NO")+"'" );
//		dQuery3.append(" \n   AND car.PTL_ID='"+carmn_Session.getString("PTL_ID")+"'" );
//		dQuery3.append(" \n   AND car.USE_INTT_ID ='"+carmn_Session.getString("USE_INTT_ID")+"'" );
//		
//		
		
			    String dQuery3_enc = "";
		dQuery3_enc = dQuery3.toString().replaceAll("*.GAS_CARD_NO", "DECRYPT(GAS_CARD_NO)");
		System.out.println(dQuery3_enc);
//		if(VENDER.getVender("CARMN_DB") == VENDER.SAP){
//			dQuery3_enc = dQuery3.toString();
//		}else{
//			dQuery3_enc = dQuery3.toString().replaceAll("GAS_CARD_NO", "DECRYPT(GAS_CARD_NO)");
//		}
//		
//		dQuery3.append(" \n   order by car.car_no ");
//
//		dynamic3.setSQL(dQuery3_enc);
	}
}
