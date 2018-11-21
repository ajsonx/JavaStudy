package day26;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 测试使用XPath方式，获取到元素或属性
 * 了解即可
 * 需要额外引入：jaxen-1.1.1.jar
 */
public class TestXPath {
	public static void main(String[] args) throws DocumentException {
		
		//1. 将文档读入变为Document树状结构对象
		SAXReader reader = new SAXReader();
		Document document = 
				reader.read(new File("books.xml"));
		
		//2. 使用XPath进行检索元素
		Element ele = document.elementByID("2");
		System.out.println(ele);//null
		
		String xpath = "//@id";//查找某个属性
		List list = document.selectNodes(xpath);
		System.out.println(list);
		//[org.dom4j.tree.DefaultAttribute@470e2030 
		//		[Attribute: name id value "2"]]
		System.out.println("-------------");
		
		xpath = "/bookstore/book";
				//以绝对路径的形式查找到所有元素：<book>元素共4个
		xpath = "bookstore";//<bookstore>元素就1个
		xpath = "book";   //没有找到，当前位置是指文档根部
		xpath = "//book"; //查找出所有<book>元素，不考虑位置 
		xpath = "//title";  //查找出所有书的标题
		
		xpath = "//@lang";  //查找所有lang属性，@表示查找属性
		
		xpath = "//book[1]";//使用谓语，来筛选查找的结果
		xpath = "//book[last()]"; //定位到最后一本书这个元素
		xpath = "//book[position()<3]"; //获得前两个元素
		
		xpath = "//book[@id]"; //查找所有书，并且带有id属性
		xpath = "//book[@id='2']"; //指定属性值，来查找元素
		
		xpath = "//book[price>35.00]";
					//查找<book>元素，条件是价格大于35元
		xpath = "//book[price>35.00]/title";
					//查找所有书的<title>元素，条件是价格大于35元
		
		xpath = "/bookstore/*";//查找<bookstore>中所有子元素
		xpath = "//book[@id='2']/*";
			//查找到id为2元素的子元素：<title><author><year><price>
		
		xpath = "//title[@*]";//查找出带有属性的<title>元素
				
		list = document.selectNodes(xpath);
		for(Object obj : list) {
			System.out.println(obj);
			//String text = ((Element)obj).getText();
			//System.out.println(text);
		}
		System.out.println("-------------");
	}
}







