package com.stream.app.wizstream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stream.app.wizstream.services.VideoService;

@SpringBootTest
class WizstreamApplicationTests {

	@Autowired
	VideoService videoService;

	@Test
	void contextLoads() {

		videoService.processVideo("<your_video_id>", null);
	}

}
