package com.example.zzan.mypage.dto;


import com.example.zzan.blacklist.dto.BlacklistDto;
import com.example.zzan.follow.dto.FollowResponseDto;
import com.example.zzan.user.entity.User;
import com.example.zzan.userHistory.dto.UserHistoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MyPageResponseDto {

	private String userImageUrl;
	private String username;
	private int alcohol;
	private String socialProvider;
	private List<UserHistoryDto> meetedUser;
	 private List<FollowResponseDto>followingUser;
	 private List<BlacklistDto>blacklistedUser;

	public MyPageResponseDto(User myPage,int alcohol,String socialProvider,List<UserHistoryDto> meetedUser,List<FollowResponseDto> followResponseDtos,List<BlacklistDto> blacklistDtos){
		this.userImageUrl= myPage.getUserImage();
		this.username= myPage.getUsername();
		this.alcohol=alcohol;
		this.socialProvider=socialProvider;
		this.meetedUser=meetedUser;
		this.followingUser=followResponseDtos;
		this.blacklistedUser=blacklistDtos;

	}

	public MyPageResponseDto(User myPage) {
		this.userImageUrl= myPage.getUserImage();
		this.username= myPage.getUsername();
	}


}
