package day26;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *  
 *
 */
public class TestReadWriteXML {
	public List<Emp> readFromXML() throws DocumentException{
		List<Emp> list = new ArrayList<Emp>();
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("emp.xml"));
		
		Element rootEle = document.getRootElement();
		List<Element> eleList = rootEle.elements();
		for(Element empEle : eleList){
			String idStr = empEle.attribute("id").getValue();
			int id = Integer.parseInt("name");
			String name = empEle.elementText("name");
			String gender = empEle.elementText("gender");
			String salaryStr = empEle.elementText("salaryStr");
			double salary = Double.parseDouble("salaryStr");
			int age = Integer.parseInt("age");
			Emp emp = new Emp(id,age,name,gender,salary);
			
		}
		return null;
	}
	/**
	 * 使用dom4j工具，将一个Document树状结构对象，写出到xml中
	 * 步骤：
	 * 1. 先创建一个空的Document树状结构文档对象
	 * 2. 添加一个根元素
	 * 3. 向根元素中，添加子元素
	 * 4. 树状结构组织好之后，就可以写出到文件中
	 */
	public void writeToXML(List<Emp> list){
		
		Document document = DocumentHelper.createDocument();
		Element rootEle = document.addElement("EmpList");
			//向document中添加根元素，只能调用一次！！
		for(Emp emp : list){
			Element empEle = rootEle.addElement("emp");
				//向根元素添加一个新元素，同时返回这个元素
			empEle.addAttribute("id", emp.getId()+"");
				//添加属性名，属性值
			
			//添加id的子元素
			Element nameEle = empEle.addElement("name");
			nameEle.addText(emp.getName());
			
			//采用链式写法添加子元素
			empEle.addElement("age").addText(emp.getAge()+"");
			empEle.addElement("gender").addText(emp.getGender());
			empEle.addElement("salary").addText(emp.getSalary()+"");
			
		}
		try {
			FileOutputStream fos = new FileOutputStream("emp-new.xml");
			OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
			XMLWriter xmlWriter = new XMLWriter(osw);
			
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Emp emp = new Emp(2,20,"hjx","15",1.5);
		TestReadWriteXML test = new TestReadWriteXML();
		List<Emp> list = new ArrayList<Emp>();
		list.add(emp);
		test.writeToXML(list);
	}
}
