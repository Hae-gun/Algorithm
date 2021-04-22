package sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ContinueCheck {
	
	public static List<Article> article = new ArrayList<Article>();; 
	public static void main(String[] args) {
		
		
		String MIN = "20211213";
		String MAX = "20211228";
		String[] set1 = {"a","b","c"};
		String[] set2 = {"a","Java","c"};
		List<String> case1 = Arrays.asList(set1);
		List<String> case2 = Arrays.asList(set2);
		Article a = new Article("a","a",case1);
		Article b = new Article("b","b",case2);
		Article c = new Article("c","c",case2);
		article.add(a);
		article.add(b);
		article.add(c);
		
		System.out.println(getAllJavaArticles());
		System.out.println(groupByAuthor());
		System.out.println(getDistinctTags());
	}
	
	public static List<Article> getAllJavaArticles(){
		return article.stream().filter(article -> article.getTags().contains("Java")).collect(Collectors.toList());
	}
	public static Map<List<String>, List<Article>> groupByAuthor(){
		return article.stream().collect(Collectors.groupingBy(Article::getTags));
	}
	public static Set<String> getDistinctTags(){
		return article.stream().flatMap(article -> article.getTags().stream())
				.collect(Collectors.toSet());
	}
}

class Article {

    private final String title;
    private final String author;
    private final List<String> tags;

    Article(String title, String author, List<String> tags) {
        this.title = title;
        this.author = author;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getTags() {
        return tags;
    }
    
    @Override
    public String toString() {
    	return getTitle() + " - " + getAuthor() + " - " + getTags() + " - ";
    }
}



