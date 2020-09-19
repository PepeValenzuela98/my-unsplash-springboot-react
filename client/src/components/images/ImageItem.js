import React, { useContext } from "react";
import styled from "@emotion/styled";
import DeleteOutline from "@material-ui/icons/DeleteOutline";
import { fadeIn } from "../UI/Animations";

import modalContext from "../../context/modal/modalContext";
import DeleteImage from "./DeleteImage";

const ImageItem = ({ imgData }) => {
  const { showModal } = useContext(modalContext);

  const handleClick = () => {
    showModal("Are you sure?", <DeleteImage image={imgData} />);
  };
  return (
    <ImageItemContainer>
      <Image src={imgData.url} alt="image" />
      <DeleteButton onClick={handleClick}>
        <DeleteOutline /> delete
      </DeleteButton>
      <Label>{imgData.label}</Label>
    </ImageItemContainer>
  );
};

const ImageItemContainer = styled.div`
  animation: ${fadeIn} 2s;
  position: relative;
  margin-top: 8px;
  vertical-align: middle;
  width: 100%;
  &:hover {
    > img {
      filter: brightness(50%);
    }

    > button {
      opacity: 1;
      visibility: visible;
    }

    > span {
      opacity: 1;
      visibility: visible;
    }
  }
`;

const Image = styled.img`
  width: 100%;
  border-radius: 20px;
  transition: filter 0.5s linear;
`;

const DeleteButton = styled.button`
  right: 15px;
  top: 15px;
  font-family: "Montserrat", sans-serif;
  font-weight: 600;
  font-size: 18px;
  border: 1px solid #eb5757;
  border-radius: 12px;
  padding: 3px 10px;
  background: none;
  color: #eb5757;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background-color 0.5s, color 0.5s linear, visibility 0s,
    opacity 0.5s linear;
  position: absolute;
  visibility: hidden;
  opacity: 0;

  &:hover {
    background-color: #eb5757;
    color: white;
  }
`;

const Label = styled.span`
  bottom: 25px;
  left: 15px;
  width: 300px;
  color: #fff;
  position: absolute;
  visibility: hidden;
  opacity: 0;
  transition: visibility 0s, opacity 0.5s linear;
  font-family: "Montserrat", sans-serif;
  font-weight: 500;
`;

export default ImageItem;
