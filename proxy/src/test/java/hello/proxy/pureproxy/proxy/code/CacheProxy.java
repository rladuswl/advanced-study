package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject {

    private Subject target; // 실제 객체 - 프록시가 호출하는 대상
    private String cacheValue; // 캐시하는 데이터

    // proxy가 RealSubject를 참조하기 위한 코드
    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("프록시 호출");
        if (cacheValue == null) { // 처음 호출하면 캐시에 데이터가 없으므로..
            cacheValue = target.operation();
        }
        return cacheValue;
    }
}
