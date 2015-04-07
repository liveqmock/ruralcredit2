package com.creditease.rc.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.vo.EasyUITree;
import com.creditease.rc.vo.NodeAttribute;

@Controller
@RequestMapping("myController")
public class MyController {

	@RequestMapping(value = "test")
	public @ResponseBody
	List<EasyUITree> test() {

		List<EasyUITree> easyUITrees = new ArrayList<EasyUITree>();

		List<EasyUITree> easyUITreesAdd = new ArrayList<EasyUITree>();
		List<EasyUITree> easyUITreesAdd2 = new ArrayList<EasyUITree>();

		NodeAttribute nodeAttribute = new NodeAttribute();

		nodeAttribute.setUrl("myController/test.do");

		EasyUITree easyUITree0 = new EasyUITree();

		easyUITree0.setId("1");
		easyUITree0.setText("项目1");

		easyUITree0.setState("open");
		easyUITree0.setAttributes(nodeAttribute);
		easyUITree0.setChildren(easyUITreesAdd);

		EasyUITree easyUITree1 = new EasyUITree();
		easyUITree1.setId("11");
		easyUITree1.setText("项目11");
		easyUITree1.setAttributes(nodeAttribute);
		easyUITreesAdd.add(easyUITree1);

		EasyUITree easyUITree2 = new EasyUITree();
		easyUITree2.setId("12");
		easyUITree2.setText("项目12");
		easyUITree2.setAttributes(nodeAttribute);
		easyUITreesAdd.add(easyUITree2);

		EasyUITree easyUITree3 = new EasyUITree();

		easyUITree3.setId("2");
		easyUITree3.setText("项目2");
		easyUITree3.setState("close");
		easyUITree3.setAttributes(nodeAttribute);
		easyUITree3.setChildren(easyUITreesAdd2);

		EasyUITree easyUITree4 = new EasyUITree();
		easyUITree4.setId("21");
		easyUITree4.setText("项目21");
		easyUITree4.setAttributes(nodeAttribute);
		easyUITreesAdd2.add(easyUITree4);

		EasyUITree easyUITree5 = new EasyUITree();
		easyUITree5.setId("22");
		easyUITree5.setText("项目22");
		easyUITree5.setAttributes(nodeAttribute);
		easyUITreesAdd2.add(easyUITree5);

		EasyUITree easyUITreeAll = new EasyUITree();
		easyUITreeAll.setId("");
		easyUITreeAll.setText("全部");

		easyUITrees.add(easyUITreeAll);
		easyUITrees.add(easyUITree0);
		easyUITrees.add(easyUITree3);

		// String s =
// "\"[{'id':1,'text':'Folder1','iconCls':'icon-save','children':[{'text':'File1','checked':true},{'text':'Books','state':'open','attributes':{'url':'/demo/book/abc','price':100},'children':[{'text':'PhotoShop','checked':true},{'id': 8,'text':'Sub Bookds','state':'closed'}]}]},{'text':'Languages','state':'closed','children':[{'text':'Java'},{'text':'C#'}]}]\"";

		return easyUITrees;
	}

	@RequestMapping(value = "testAll")
	public @ResponseBody
	List<EasyUITree> testAll() {
		List<EasyUITree> easyUITreesAll = new ArrayList<EasyUITree>();
		EasyUITree easyUITreeAll = new EasyUITree();
		easyUITreeAll.setId("");
		easyUITreeAll.setText("全部");
		easyUITreeAll.setChecked("true");
		List<EasyUITree> easyUITrees = new ArrayList<EasyUITree>();
		easyUITreeAll.setChildren(easyUITrees);
		EasyUITree easyUITree1 = new EasyUITree();
		EasyUITree easyUITree2 = new EasyUITree();
		easyUITrees.add(easyUITree1);
		easyUITrees.add(easyUITree2);
		List<EasyUITree> easyUITrees1 = new ArrayList<EasyUITree>();
		List<EasyUITree> easyUITrees2 = new ArrayList<EasyUITree>();
		easyUITree1.setId("1");
		easyUITree1.setText("项目1");
		easyUITree1.setChildren(easyUITrees1);
		easyUITree2.setId("2");
		easyUITree2.setText("项目2");
		easyUITree2.setChildren(easyUITrees2);
		EasyUITree easyUITree11 = new EasyUITree();
		EasyUITree easyUITree12 = new EasyUITree();
		EasyUITree easyUITree21 = new EasyUITree();
		EasyUITree easyUITree22 = new EasyUITree();
		easyUITrees1.add(easyUITree11);
		easyUITrees1.add(easyUITree12);
		easyUITrees2.add(easyUITree21);
		easyUITrees2.add(easyUITree22);
		easyUITree11.setId("11");
		easyUITree11.setText("项目11");
		easyUITree12.setId("12");
		easyUITree12.setText("项目12");
		easyUITree21.setId("21");
		easyUITree21.setText("项目21");
		easyUITree22.setId("22");
		easyUITree22.setText("项目22");
		easyUITreesAll.add(easyUITreeAll);
		return easyUITreesAll;
	}
}
