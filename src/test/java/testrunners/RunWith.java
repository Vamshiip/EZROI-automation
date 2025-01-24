package testrunners;

import io.cucumber.junit.Cucumber;

public @interface RunWith {

	Class<Cucumber> value();

}
