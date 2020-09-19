import React, { useContext, useState } from "react";
import {
  FieldContainer,
  InputModal,
  LabelModal,
  CancelButtonModal,
  ButtonSubmitModal,
} from "../UI/FormStyledComponents";

import { ErrorField } from "../UI/FormComponents";

import imageContext from "../../context/image/imageContext";
import modalContext from "../../context/modal/modalContext";

const DeleteImage = ({ image }) => {
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const { deleteImage } = useContext(imageContext);
  const { hideModal } = useContext(modalContext);

  const handleChange = (e) => {
    setPassword(e.target.value);
  };

  const handleClick = () => {
    hideModal();
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    deleteImage(password, image, hideModal, setErrorMessage);
  };
  return (
    <form onSubmit={handleSubmit}>
      <FieldContainer>
        <LabelModal htmlFor="password">Password</LabelModal>
        <InputModal
          type="password"
          id="password"
          name="password"
          value={password}
          onChange={handleChange}
        />
        <ErrorField
          condition={errorMessage.trim() === ""}
          message={errorMessage}
        />
      </FieldContainer>
      <div style={{ display: "flex", justifyContent: "flex-end" }}>
        <CancelButtonModal type="button" onClick={handleClick}>
          Cancel
        </CancelButtonModal>
        <ButtonSubmitModal backgroundColor="#EB5757" type="submit">
          Submit
        </ButtonSubmitModal>
      </div>
    </form>
  );
};

export default DeleteImage;
