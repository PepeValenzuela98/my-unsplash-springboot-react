import React, { useState, useContext, useEffect } from "react";
import styled from "@emotion/styled";
import SearchIcon from "@material-ui/icons/Search";
import CancelIcon from "@material-ui/icons/Cancel";
import imageContext from "../context/image/imageContext";

const Filter = () => {
  const [filterLabel, setFilterLabel] = useState("");

  const { filterImage, quitFilterImage, imagesFiltered } = useContext(
    imageContext
  );

  useEffect(() => {
    if (!imagesFiltered) {
      setFilterLabel("");
    }
  }, [imagesFiltered]);

  const handleClick = () => {
    setFilterLabel("");
    quitFilterImage();
  };

  const handleKeyUp = (e) => {
    if (e.key === "Enter" || e.keyCode === 13) {
      filterImage(filterLabel);
    }
  };

  const handleChange = (e) => {
    setFilterLabel(e.target.value);
  };
  return (
    <SearcherContainer>
      <Searcher>
        <SearchIcon />
        <input
          type="text"
          placeholder="Search by name"
          onKeyUp={handleKeyUp}
          onChange={handleChange}
          value={filterLabel}
        />
        {imagesFiltered && (
          <CancelIcon
            style={{ cursor: "pointer", color: "#EB5757" }}
            onClick={handleClick}
          />
        )}
      </Searcher>
    </SearcherContainer>
  );
};

const SearcherContainer = styled.div`
  flex: 1;
  display: flex;
  align-items: center;
  color: #bdbdbd;
`;

const Searcher = styled.div`
  border: 1px solid #bdbdbd;
  border-radius: 18px;
  padding: 10px;
  flex: 0 0 200px;
  display: flex;
  align-items: center;
  input {
    border: none;
    margin-left: 10px;
    &::placeholder {
      font-family: "Noto Sans", sans-serif;
      color: #bdbdbd;
    }
  }
`;

export default Filter;
