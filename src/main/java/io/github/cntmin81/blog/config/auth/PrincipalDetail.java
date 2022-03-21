package io.github.cntmin81.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.github.cntmin81.blog.model.User;
import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 롼료가 되면 UserDetails타입의 요브젝트를 
// 스프링 시큐리티 고유의 세션저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails {

	private User user; // 콤포지션 - 객체를 품고 있다.
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	// 계정이 만료되었는지 리턴한다.(true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정이 잠겨있지 않았는지 리턴한다.(true:잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	// 비밀번호가 만료되었는지 리턴한다.
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정이 활성화 인지 리턴한다.
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정이 가지고 있는 권한 목록을 리턴한다. (권한이 여러개 있어서 루프를 돌아야 하는데 우리는 한개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				// ROLE_는 쪽 넣어줘야 한다.(스프 규칙)
//				return "ROLE_"+user.getRole();
//			}
//		});
		
		collectors.add(()->{
			return "ROLE_"+user.getRole();
		});
		
		return collectors;
	}
}
