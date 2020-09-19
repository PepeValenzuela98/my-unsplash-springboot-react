import styled from "@emotion/styled";
import { fadeIn } from "./Animations";
import { Link } from "react-router-dom";

export const FormAuthContainer = styled.div`
  animation: ${fadeIn} 1s;
  height: 100vh;
  min-height: 800px;
  display: grid;
  place-items: center;
`;

export const FormAuth = styled.form`
  border: 1px solid #d6d6d6;
  display: flex;
  flex-direction: column;
  padding: 5rem 3rem;
  max-width: 500px;
  width: 95%;
  border-radius: 1rem;
`;

export const LabelAuth = styled.label`
  font-weight: 700;
  margin-bottom: 0.5rem;
`;

export const InputAuth = styled.input`
  border: 1px solid #d6d6d6;
  padding: 10px;
`;

export const ButtonAuth = styled.button`
  margin-bottom: 2rem;
  padding: 10px;
  border: none;
  border-radius: 8px;
  background-color: #111111;
  color: #fff;
  font-size: 16px;
  &:hover {
    background-color: #272727;
    cursor: pointer;
  }
`;

export const LabelModal = styled.label`
  font-family: "Noto Sans";
  font-weight: 500;
  font-size: 18px;
  margin-bottom: 15px;
  color: #4f4f4f;
`;

export const InputModal = styled.input`
  border: 1px solid #4f4f4f;
  padding: 20px;
  border-radius: 12px;
  width: 500px;
  font-family: "Noto Sans";
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 19px;

  color: #bdbdbd;

  &::placeholder {
    color: #bdbdbd;
    font-family: Noto Sans;
    font-style: normal;
    font-weight: 500;
  }
`;

export const ButtonSubmitModal = styled.button`
  background-color: ${(props) => props.backgroundColor};
  box-shadow: 0px 1px 6px rgba(0, 0, 0, 0.1);
  border: none;
  border-radius: 28px;
  padding: 20px 30px;
  font-weight: 700;
  font-family: "Noto Sans", sans-serif;
  font-size: 18px;
  color: white;
  cursor: pointer;
  transition: opacity 0.5s, box-shadow 0.5s;
  margin-left: 30px;

  &:hover {
    opacity: 0.8;
    box-shadow: 0px 1px 6px rgba(170, 170, 170, 0.8);
  }
`;

export const CancelButtonModal = styled.button`
  border: none;
  background: none;
  color: #bdbdbd;
  font-family: Noto Sans;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  cursor: pointer;
`;

export const AuthLink = styled(Link)`
  color: #767676;

  &:hover {
    color: #000000;
  }
`;

export const FieldContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 2rem;
`;

export const ErrorMessage = styled.span`
  color: #fd4848;
`;
