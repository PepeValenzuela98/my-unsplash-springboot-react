import React, { Fragment, useContext, useEffect } from "react";
import { useHistory } from "react-router-dom";

import useValidation from "../../hooks/useValidation";
import validateLogin from "../../validations/validateLogin";
import authContext from "../../context/auth/authContext";
import { ErrorField } from "../UI/FormComponents";
import {
  FormAuthContainer,
  FormAuth,
  FieldContainer,
  LabelAuth,
  InputAuth,
  ButtonAuth,
  AuthLink,
} from "../UI/FormStyledComponents";

const Login = () => {
  const inialState = { email: "", password: "" };
  const { login, authenticated, token } = useContext(authContext);

  const history = useHistory();
  useEffect(() => {
    if (authenticated || token) {
      history.push("/images");
    }
    // eslint-disable-next-line
  }, [authenticated]);

  const loginFn = () => {
    login(values);
  };

  const { values, errors, handleSubmit, handleChange } = useValidation(
    inialState,
    validateLogin,
    loginFn
  );

  const { email, password } = values;

  return (
    <Fragment>
      <FormAuthContainer>
        <FormAuth onSubmit={handleSubmit}>
          <h1 style={{ textAlign: "center" }}>Login</h1>

          <FieldContainer>
            <LabelAuth htmlFor="email">Email</LabelAuth>
            <InputAuth
              type="text"
              id="email"
              name="email"
              onChange={handleChange}
              value={email}
            />
            <ErrorField condition={!errors.email} message={errors.email} />
          </FieldContainer>
          <FieldContainer>
            <LabelAuth htmlFor="password">Password</LabelAuth>
            <InputAuth
              type="password"
              id="password"
              name="password"
              onChange={handleChange}
              value={password}
            />
            <ErrorField
              condition={!errors.password}
              message={errors.password}
            />
          </FieldContainer>
          <ButtonAuth type="submit">Login</ButtonAuth>
          <div style={{ textAlign: "center" }}>
            <span>
              Don't have an account? <AuthLink to="/register">Join</AuthLink>
            </span>
            <br></br>
            <span>JAEV CODE @DevChallenges.io</span>
          </div>
        </FormAuth>
      </FormAuthContainer>
    </Fragment>
  );
};

export default Login;
