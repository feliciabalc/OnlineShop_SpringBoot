package map.project.demo;

import map.project.demo.DB_Controller.OrderController;
import map.project.demo.Entities.Orders;
import map.project.demo.Entities.Review;
import map.project.demo.Entities.ReviewProxy;
import map.project.demo.Service.OrderService;
import map.project.demo.Service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {
	Review realReview = new Review(100L, "5 stars", "Great product!", "2023-12-12");
	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private ReviewService reviewService;
	@Test
	void proxyTest() {
		ReviewProxy reviewProxy = new ReviewProxy(realReview);

		assertEquals(realReview.getStars(), reviewProxy.getStars());

		realReview.setStars("2 Stars");
		ReviewProxy reviewProxy2 = new  ReviewProxy(realReview);

		assert(reviewProxy2.getStars().contains("Hidden"));
	}

	@Test
	void loadTest() throws Exception {
		Mockito.when(reviewService.getReviewById(7L)).thenReturn(realReview);

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/review/7"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.stars").value("5 stars"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("de calitate"));
	}

}
