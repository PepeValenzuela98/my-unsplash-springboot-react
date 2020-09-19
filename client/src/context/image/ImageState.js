import React, { useReducer } from "react";

import imageReducer from "./imageReducer";
import ImageContext from "./imageContext";

import {
  GET_IMAGES,
  SUCCESS_GET_IMAGES,
  ERROR_GET_IMAGES,
  ADD_IMAGE,
  SUCCESS_ADD_IMAGE,
  ERROR_ADD_IMAGE,
  DELETE_IMAGE,
  SUCCESS_DELETE_IMAGE,
  ERROR_DELETE_IMAGE,
  FILTER_IMAGE,
  QUIT_FILTER_IMAGE,
} from "../../types/index";

import axios from "../../config/axios";

const ImageState = (props) => {
  const initialState = {
    images: [],
    imagesFiltered: null,
    loading: null,
    error: null,
  };

  const [state, dispatch] = useReducer(imageReducer, initialState);

  const getImages = async () => {
    dispatch({ type: GET_IMAGES });
    try {
      const response = await axios.get("/photo/");
      const images = response.data;
      dispatch({ type: SUCCESS_GET_IMAGES, payload: images });
    } catch (error) {
      dispatch({ type: ERROR_GET_IMAGES });
    }
  };

  const addImageUrl = async (image) => {
    dispatch({ type: ADD_IMAGE });
    try {
      image = { label: image.label, url: image.photoUrl };
      const response = await axios.post("/photo/", image);
      const newImage = response.data.photo;
      dispatch({ type: SUCCESS_ADD_IMAGE, payload: newImage });
    } catch (error) {
      dispatch({ type: ERROR_ADD_IMAGE });
    }
  };

  const addImageUpload = async (image) => {
    dispatch({ type: ADD_IMAGE });
    try {
      let newImage = {
        label: image.label,
        url: `${process.env.REACT_APP_BACKEND_URL}/upload/`,
      };

      const responseNewImage = await axios.post("/photo/", newImage);
      newImage = responseNewImage.data.photo;

      let formData = new FormData();
      formData.append("file", image.uploadFile);
      formData.append("id", newImage.id);
      await axios.post(`/upload/`, formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      dispatch({ type: SUCCESS_ADD_IMAGE, payload: newImage });
    } catch (error) {
      dispatch({ type: ERROR_ADD_IMAGE });
    }
  };

  const deleteImage = async (password, image, hideModal, setErrorMessage) => {
    dispatch({ type: DELETE_IMAGE });

    try {
      await axios.post("/photo/delete", { password, id: image.id });
      if (image.url.includes(process.env.REACT_APP_BACKEND_URL)) {
        await axios.delete(`/upload/${image.id}`);
      }
      dispatch({ type: SUCCESS_DELETE_IMAGE, payload: image });
      hideModal();
    } catch (error) {
      dispatch({ type: ERROR_DELETE_IMAGE });
      setErrorMessage("Wrong password");
    }
  };

  const filterImage = async (label) => {
    dispatch({ type: FILTER_IMAGE, payload: label });
  };

  const quitFilterImage = () => {
    dispatch({ type: QUIT_FILTER_IMAGE });
  };

  return (
    <ImageContext.Provider
      value={{
        images: state.images,
        imagesFiltered: state.imagesFiltered,
        loading: state.loading,
        error: state.error,

        getImages: getImages,
        addImageUrl: addImageUrl,
        addImageUpload: addImageUpload,
        deleteImage: deleteImage,
        filterImage: filterImage,
        quitFilterImage: quitFilterImage,
      }}
    >
      {props.children}
    </ImageContext.Provider>
  );
};

export default ImageState;
