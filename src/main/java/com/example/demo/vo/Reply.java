package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // toString, getter, setter 메서드를 자동으로 만들어줌
@AllArgsConstructor // 사용자가 도메인에 값을 요청하면 알아서 생성자를 만듦
@NoArgsConstructor  // 기본생성자를 생성. 즉 기본생성자도 있고 사용자가 값을 넣었을 때 자동으로 만드는 생성자도 
public class Reply {

	private int id;
	private int memberId;
	private String relTypeCode;
	private int relId;
	private String replyText;
	private String regDate;
	private String updateDate;
	
	private String nickName;
	
	public String getForPrintText() {
		return this.replyText.replaceAll("\n", "<br>");
		// replyText가 가지고 있는 값중에서 첫번째 인자에 값이 존재하면 
		// 두번째 인자로 값을 다 바꾸겠다.
	}

}