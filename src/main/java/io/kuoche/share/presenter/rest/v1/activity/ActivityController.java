package io.kuoche.share.presenter.rest.v1.activity;

import io.kuoche.share.core.usercase.activity.CreateActivityUseCase;
import io.kuoche.share.core.usercase.activity.GetActivityUseCase;
import io.kuoche.share.presenter.rest.v1.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/activity")
@RequiredArgsConstructor
public class ActivityController {
    private final CreateActivityUseCase createActivityUseCase;
    private final GetActivityUseCase getActivityUseCase;


    @PostMapping("")
    public Response<ActivityResponse> createActivity(@RequestBody ActivityRequest request){
        CreateActivityUseCase.OutputValues response = createActivityUseCase.execute(request.fromThis());
        return Response.ok(ActivityResponse.from(response.getActivity()));
    }

    @GetMapping("/{id}")
    public Response<ActivityResponse> viewActivity(@PathVariable Long id){
        GetActivityUseCase.OutputValues response = getActivityUseCase.execute(
                new GetActivityUseCase.InputValues(id));
        return Response.ok(ActivityResponse.from(response.getActivity()));
    }

}
