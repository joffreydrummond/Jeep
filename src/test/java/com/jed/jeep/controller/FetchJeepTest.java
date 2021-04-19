// package com.jed.jeep.controller;
//
// import com.jed.jeep.controller.support.FetchJeepTestSupport;
// import com.jed.jeep.entity.Jeep;
// import com.jed.jeep.entity.JeepModel;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//
// import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import static org.assertj.core.api.Assertions.assertThat;
//
// @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// class FetchJeepTest extends FetchJeepTestSupport{
//
//  @Test
//  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
//
//    //given:
//
//    JeepModel model = JeepModel.CHEROKEE;
//    String trim = "Sport";
//    String uri =
//            String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//
//
//    //when:
//    ResponseEntity <Jeep> res =
//            getRestTemplate().getForEntity(uri, Jeep.class);
//
//    //then:
//    assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//  }
//
// }

package com.jed.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.jed.jeep.controller.support.FetchJeepTestSupport;
import com.jed.jeep.entity.Jeep;
import com.jed.jeep.entity.JeepModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FetchJeepTest extends FetchJeepTestSupport {

  @Test
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {

    // given: a valid model, trim and uri

    JeepModel model = JeepModel.WRANGLER;
    String trim = "Sport";
    String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);

    // when: a connection is made to uri
        ResponseEntity<Jeep> res = getRestTemplate().getForEntity(uri, Jeep.class);

    // then: a valid status code is returned OK 200
       assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
