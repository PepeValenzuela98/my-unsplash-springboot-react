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

export default (state, action) => {
  switch (action.type) {
    case GET_IMAGES:
    case ADD_IMAGE:
    case DELETE_IMAGE:
      return { ...state, loading: true };

    case SUCCESS_GET_IMAGES:
      return { ...state, images: action.payload, loading: false, error: false };

    case SUCCESS_ADD_IMAGE:
      return {
        ...state,
        loading: false,
        error: false,
        images: [action.payload, ...state.images],
        imagesFiltered: null,
      };

    case SUCCESS_DELETE_IMAGE:
      return {
        ...state,
        loading: false,
        error: false,
        images: state.images.filter((image) => image.id !== action.payload.id),
        imagesFiltered: state.imagesFiltered
          ? state.imagesFiltered.filter(
              (image) => image.id !== action.payload.id
            )
          : null,
      };

    case FILTER_IMAGE:
      return {
        ...state,
        imagesFiltered: state.images.filter((image) =>
          image.label.toLowerCase().includes(action.payload.toLowerCase())
        ),
      };

    case QUIT_FILTER_IMAGE:
      return { ...state, imagesFiltered: null };

    case ERROR_GET_IMAGES:
    case ERROR_ADD_IMAGE:
    case ERROR_DELETE_IMAGE:
      return {
        ...state,
        error: true,
        loading: false,
      };

    default:
      return state;
  }
};
