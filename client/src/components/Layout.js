import React, { useContext } from "react";
import styled from "@emotion/styled";

import Logo from "../images/my_unsplash_logo.svg";
import modalContext from "../context/modal/modalContext";
import authContext from "../context/auth/authContext";
import NewImage from "./images/NewImage";
import Filter from "./Filter";

const Layout = () => {
  const { showModal } = useContext(modalContext);
  const { logout } = useContext(authContext);

  const handleClick = () => {
    showModal("Add a new photo", <NewImage />);
  };

  return (
    <LayoutContainer>
      <img style={{ width: "150px" }} src={Logo} alt="logo" />
      <Filter />
      <AddPhoto onClick={handleClick}>Add a photo</AddPhoto>
      <Logout onClick={logout}>Logout</Logout>
    </LayoutContainer>
  );
};

const LayoutContainer = styled.div`
  padding: 20px;
  display: flex;
  align-items: center;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
`;

const AddPhoto = styled.button`
  background-color: #3db46d;
  box-shadow: 0px 1px 6px rgba(0, 0, 0, 0.1);
  border: none;
  border-radius: 28px;
  padding: 20px;
  font-weight: 700;
  font-family: "Noto Sans", sans-serif;
  font-size: 18px;
  color: white;
  cursor: pointer;
  transition: background-color 0.5s, box-shadow 0.5s;
  margin-right: 10px;
  &:hover {
    background-color: #5bc887;
    box-shadow: 0px 1px 6px rgba(170, 170, 170, 0.8);
  }
`;

const Logout = styled.span`
  background-color: #111111;
  box-shadow: 0px 1px 6px rgba(0, 0, 0, 0.1);
  border: none;
  border-radius: 28px;
  padding: 15px;
  font-weight: 500;
  font-family: "Noto Sans", sans-serif;
  font-size: 14px;
  color: white;
  cursor: pointer;
  transition: background-color 0.5s, box-shadow 0.5s;
  &:hover {
    opacity: 0.8;
    box-shadow: 0px 1px 6px rgba(170, 170, 170, 0.8);
  }
`;

export default Layout;
