package ra.model.Credentials.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ra.model.Credentials.PointsCredentials;
import ra.model.serviceImp.PointServiceImp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
public class PointCredentialsValidator implements Validator {

    private final PointServiceImp pointService;

    public PointCredentialsValidator(PointServiceImp pointService) {
        this.pointService = pointService;
    }

    @NotNull
    @NotEmpty
    @Override
    public boolean supports(Class<?> aClass) {
        return PointsCredentials.class.equals(aClass);
    }

    @NotNull
    @NotEmpty
    @Override
    public void validate(Object o, Errors errors) {
        if (!errors.hasErrors()){
            PointsCredentials pointsCredentials = (PointsCredentials) o;
            try {
                Double.parseDouble(pointsCredentials.getX());
                Double.parseDouble(pointsCredentials.getY());
                Double.parseDouble(pointsCredentials.getR());
            }catch (Exception exception){
                exception.printStackTrace();
                errors.rejectValue("point","point.error", "invalid values in point");
            }
        }
    }
}
