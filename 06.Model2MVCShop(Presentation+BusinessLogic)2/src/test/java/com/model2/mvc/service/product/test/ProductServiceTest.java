package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-aspect.xml" , "classpath:config/context-common.xml"
		,"classpath:config/context-mybatis.xml" , "classpath:config/context-transaction.xml"})
public class ProductServiceTest {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//	@Test
	//	public void testAddProduct() throws Exception {
	//
	//		Product product = new Product();
	//		product.setFileName("zzzzz");
	//		product.setManuDate("zzzzz");
	//		product.setPrice(55);
	//		product.setProdDetail("zz");
	//		product.setProdName("zzz");
	//		product.setProdNo(5);
	//
	//		productService.addProduct(product);

	// product = productService.getProduct(4);

	// ==> console Ȯ��
	//		System.out.println(product);
	//
	//		Assert.assertEquals("zzzzz", product.getFileName());
	//		Assert.assertEquals("zzzzz", product.getManuDate());
	//		Assert.assertEquals(55555, product.getPrice());
	//		Assert.assertEquals("zzzzz", product.getProdDetail());
	//		Assert.assertEquals("zzzzz", product.getProdName());
	//		Assert.assertEquals(5555, product.getProdNo());
	//	}

	@Test
	public void testGetProduct() throws Exception {

		Product product = new Product();
		product = productService.getProduct(55555);
		Assert.assertEquals("안녕하세요", product.getFileName());
		Assert.assertEquals("zzzzz", product.getManuDate());
		Assert.assertEquals(55555, product.getPrice());
		Assert.assertEquals("zzzzz", product.getProdDetail());
		Assert.assertEquals("zzzzz", product.getProdName());
		Assert.assertEquals(55555, product.getProdNo());

		Assert.assertNotNull(productService.getProduct(10));
		Assert.assertNotNull(productService.getProduct(20)); 
	}

	@Test 
	public void testUpdateUser() throws Exception{

		Product product = new Product();
		product.setProdNo(55555);
		product.setProdName("경주이에여");
		product.setProdDetail("경주요");
		product.setManuDate("12/12/12");
		product.setPrice(123);
		product.setFileName("안녕하세여");

		productService.updateProduct(product);

		product = productService.getProduct(55555);
		Assert.assertNotNull(product);

	}


	@Test
	public void testGetProductListAll() throws Exception{

		Search search = new Search(); search.setCurrentPage(1);
		search.setPageSize(3); Map<String,Object> map =
				productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list"); Assert.assertEquals(3,
				list.size());



		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setCurrentPage(1); search.setPageSize(3);
		search.setSearchCondition("0"); search.setSearchKeyword(""); map =
				productService.getProductList(search);

		list = (List<Object>)map.get("list"); Assert.assertEquals(3, list.size());

		//==> console Ȯ�� //System.out.println(list);

		totalCount = (Integer)map.get("totalCount"); System.out.println(totalCount);
	}

	@Test 
	public void testGetProductList() throws Exception{

		Search search = new Search(); 
		search.setCurrentPage(1);
		search.setPageSize(3); 
		search.setSearchCondition("0");
		search.setSearchKeyword("10000"); 
		
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list"); 
		
		Assert.assertEquals(1, list.size());

		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("0");
		search.setSearchKeyword(""+System.currentTimeMillis()); 
		
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list"); 
		Assert.assertEquals(0, list.size());

		totalCount = (Integer)map.get("totalCount"); 
		System.out.println(totalCount);
	}

	@Test 
	public void testGetUserListByUserName() throws Exception{

		Search search = new Search(); search.setCurrentPage(1);
		search.setPageSize(3); search.setSearchCondition("1");
		search.setSearchKeyword("SCOTT"); Map<String,Object> map =
				productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list"); Assert.assertEquals(3, list.size());



		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("1");
		search.setSearchKeyword(""+System.currentTimeMillis()); map =
				productService.getProductList(search);

		list = (List<Object>)map.get("list"); Assert.assertEquals(0, list.size());



		totalCount = (Integer)map.get("totalCount"); System.out.println(totalCount);
	}

}