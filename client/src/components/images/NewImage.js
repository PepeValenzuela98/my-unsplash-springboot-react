import React, { useContext } from "react";
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

import useValidation from "../../hooks/useValidation";
import validateNewImage from "../../validations/validateNewImage";

const NewImage = () => {
  const initialState = {
    label: "",
    photoUrl: "",
    uploadPhoto: "",
    uploadFile: null,
  };

  const { addImageUrl, addImageUpload } = useContext(imageContext);
  const { hideModal } = useContext(modalContext);

  const addNewImage = () => {
    if (photoUrl) {
      addImageUrl(values);
    } else {
      addImageUpload(values);
    }
    hideModal();
  };

  const handleClick = () => {
    hideModal();
  };

  const {
    values,
    errors,
    handleSubmit,
    handleChange,
    handleChangeFile,
  } = useValidation(initialState, validateNewImage, addNewImage);

  const { label, photoUrl, uploadPhoto } = values;

  return (
    <form onSubmit={handleSubmit}>
      <FieldContainer>
        <LabelModal htmlFor="label">Label</LabelModal>
        <InputModal
          type="text"
          id="label"
          name="label"
          value={label}
          onChange={handleChange}
        />
        <ErrorField condition={!errors.label} message={errors.label} />
      </FieldContainer>
      <div
        style={{
          border: "1px dashed black",
          borderRadius: "8px",
          padding: "10px",
          marginBottom: "2rem",
        }}
      >
        <FieldContainer>
          <LabelModal htmlFor="photoUrl">Photo URL</LabelModal>
          <InputModal
            type="text"
            id="photoUrl"
            name="photoUrl"
            value={photoUrl}
            onChange={handleChange}
          />
        </FieldContainer>
        <span>Or</span>
        <FieldContainer
          style={{
            marginTop: "2rem",
          }}
        >
          <LabelModal htmlFor="uploadPhoto">Photo</LabelModal>
          <InputModal
            type="file"
            id="uploadPhoto"
            name="uploadPhoto"
            value={uploadPhoto}
            onChange={handleChangeFile}
          />
        </FieldContainer>
      </div>
      <ErrorField condition={!errors.photo} message={errors.photo} />

      <div style={{ display: "flex", justifyContent: "flex-end" }}>
        <CancelButtonModal type="button" onClick={handleClick}>
          Cancel
        </CancelButtonModal>
        <ButtonSubmitModal backgroundColor="#3DB46D" type="submit">
          Submit
        </ButtonSubmitModal>
      </div>
    </form>
  );
};

export default NewImage;
