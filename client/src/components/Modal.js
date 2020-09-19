import React, { useContext, useRef, useEffect } from "react";
import styled from "@emotion/styled";
import modalContext from "../context/modal/modalContext";
import { fadeIn } from "./UI/Animations";

const Modal = () => {
  const { show, component, title, hideModal } = useContext(modalContext);
  const wrapperRef = useRef(null);

  useEffect(() => {
    function handleClickOutside(event) {
      if (wrapperRef.current && !wrapperRef.current.contains(event.target)) {
        hideModal();
      }
    }
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
    // eslint-disable-next-line
  }, [wrapperRef]);

  if (!show) {
    return null;
  }
  return (
    <ModalContainer>
      <ModalContent ref={wrapperRef}>
        <h2>{title}</h2>
        {component}
      </ModalContent>
    </ModalContainer>
  );
};

const ModalContainer = styled.div`
  position: fixed;
  left: 0;
  top: 0;
  z-index: 20;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
  display: grid;
  place-items: center;
`;

const ModalContent = styled.div`
  animation: ${fadeIn} 0.2s;

  background-color: #fff;
  padding: 40px 60px;
  border-radius: 12px;

  h2 {
    font-family: Noto Sans;
    font-weight: 500;
    font-size: 24px;
    margin-bottom: 20px;
  }
`;

export default Modal;
