package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

  // 실패 요청일 경우 json을 객체로 생성하는 것 자체가 실패, 컨트롤러로 넘어오지도 못함 (ItemSaveForm 이거 자체가 실패니까)
  @PostMapping("/add")
  public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
    log.info("API 컨트롤러 호출");

    if (bindingResult.hasErrors()) {
      log.info("검증 오류 발생 errors={}", bindingResult);
      return bindingResult.getAllErrors();
    }

    log.info("성공 로직 실행");
    return form;
  }
}
