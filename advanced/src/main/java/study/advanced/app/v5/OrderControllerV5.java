package study.advanced.app.v5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.advanced.trace.callback.TraceCallback;
import study.advanced.trace.callback.TraceTemplate;
import study.advanced.trace.logtrace.LogTrace;

// 템플릿 메서드 패턴과 콜백 패턴 끝 - v5

@RestController // @Controller + @ResponseBody
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    @Autowired // 생략 가능
    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
        return template.execute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        });
    }
}
