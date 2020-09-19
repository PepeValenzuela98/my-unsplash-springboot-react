import React, { useContext, useEffect } from "react";
import { useHistory } from "react-router-dom";

import authContext from "../../context/auth/authContext";

import useValidation from "../../hooks/useValidation";
import validateRegister from "../../validations/validateRegister";
import { ErrorField } from "../UI/FormComponents";
import {
  AuthLink,
  ButtonAuth,
  FieldContainer,
  FormAuth,
  FormAuthContainer,
  InputAuth,
  LabelAuth,
} from "../UI/FormStyledComponents";

const Register = () => {
  const inialState = { name: "", email: "", password: "", passwordRepeat: "" };
  const { registerUser, authenticated, token } = useContext(authContext);

  const history = useHistory();
  useEffect(() => {
    if (authenticated || token) {
      history.push("/images");
    }
    // eslint-disable-next-line
  }, [authenticated]);

  const registerUserFn = () => {
    registerUser(values);
  };

  const { values, errors, handleSubmit, handleChange } = useValidation(
    inialState,
    validateRegister,
    registerUserFn
  );

  const { name, email, password, passwordRepeat } = values;
  return (
    <FormAuthContainer>
      <FormAuth onSubmit={handleSubmit}>
        <h1 style={{ textAlign: "center" }}>Register</h1>
        <FieldContainer>
          <LabelAuth htmlFor="name">Name</LabelAuth>
          <InputAuth
            type="text"
            id="name"
            name="name"
            onChange={handleChange}
            value={name}
          />
          <ErrorField condition={!errors.name} message={errors.name} />
        </FieldContainer>
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
          <ErrorField condition={!errors.password} message={errors.password} />
        </FieldContainer>

        <FieldContainer>
          <LabelAuth htmlFor="passwordRepeat">Password Repeat</LabelAuth>
          <InputAuth
            type="password"
            id="passwordRepeat"
            name="passwordRepeat"
            onChange={handleChange}
            value={passwordRepeat}
          />
          <ErrorField
            condition={!errors.passwordRepeat}
            message={errors.passwordRepeat}
          />
        </FieldContainer>
        <ButtonAuth type="submit">Register</ButtonAuth>
        <div style={{ textAlign: "center" }}>
          <span>
            Do you already have an account?{" "}
            <AuthLink to="/login">Login</AuthLink>
            <br></br>
            <span>JAEV CODE @DevChallenges.io</span>
          </span>
        </div>
      </FormAuth>
    </FormAuthContainer>
  );
};

export default Register;
