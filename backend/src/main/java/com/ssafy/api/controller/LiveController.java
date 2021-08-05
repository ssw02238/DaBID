package com.ssafy.api.controller;

import com.ssafy.api.request.LiveRegisterPostReq;
import com.ssafy.api.service.LiveService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Live;
import com.ssafy.db.entity.ProductCategory;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
/**
 * 라이브 관련 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value="Live API", tags={"Live"})
@RestController
@RequestMapping("/dabid/live")
public class LiveController {
	@Autowired
	UserService userService;
	@Autowired
	LiveService liveService;
	/**
	 * 라이브 CRUD 관련 Controller
	 */
	@PostMapping("")
	@ApiOperation(value = "라이브 생성", notes = "상품 정보와 라이브 정보를 입력받아 라이브를 생성한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "에러 발생"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> createLive(
			@RequestBody @ApiParam(value="라이브 생성을 위한 정보", required = true) LiveRegisterPostReq registerInfo) {
//		System.out.println(authentication.toString());
//		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
//		System.out.println(userDetails.toString());
//		System.out.println(userDetails.getUsername());
//		String userId = userDetails.getUsername();


		User user = userService.getUserByUserId(registerInfo.getUserId());
		try {
			liveService.createLive(user, registerInfo);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(400, "라이브 생성에 실패했습니다."));
	}
	@PutMapping("/{prdId}")
	@ApiOperation(value = "라이브 수정",
			notes = "상품고유아이디(prdId)를 파라미터로 받아 통해 라이브 테이블 수정한다.")
	public ResponseEntity<?> updateLive(@PathVariable @ApiParam(name="prdId") int prdId,
		@RequestBody @ApiParam(value="라이브 생성을 위한 정보", required = true) LiveRegisterPostReq registerInfo) {

		// 파라미터로 넘어온 prdId(상품 고유 아이디)로 해당되는 Live 객체 찾기
		Live live = liveService.getLiveByPrdId(prdId);

		try {
			liveService.updateLive(registerInfo, live);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(400, "라이브 수정에 실패했습니다."));
	}
	@DeleteMapping("/{prdId}")
	@ApiOperation(value = "라이브 삭제", notes = "상품 고유 아이디를 받아 등록된 라이브를 삭제한다.")
	public ResponseEntity<? extends BaseResponseBody> deleteLive(
			@PathVariable @ApiParam(value="상품 삭제를 위한 상품 고유 아이디", readOnly = true) int prdId) {

		liveService.deleteLive(prdId);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "삭제 성공"));
	}

	@GetMapping()
	@ApiOperation(value = "라이브 top2 조회", notes = "라이브 top2 조회")
	public ResponseEntity<?> selectTop2Lives() {
		List<Live> liveList = liveService.getRecentLives(0);
		System.out.println("첫번째다 : "+liveList.toString());
		liveList.addAll(liveService.getRecentLives(1));
		System.out.println("두번째다 : "+liveList.toString());
		liveList.addAll(liveService.getRecentLives(2));
		System.out.println("세번째다 : "+liveList.toString());
		return ResponseEntity.status(200).body(liveList);
	}
}