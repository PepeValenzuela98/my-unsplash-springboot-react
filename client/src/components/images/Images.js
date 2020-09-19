import React, { Fragment, useContext, useEffect, useState } from "react";
import styled from "@emotion/styled";

import Layout from "../Layout";
import ImageItem from "./ImageItem";

import { chunkArray } from "../../util/util";
import imageContext from "../../context/image/imageContext";

const Images = () => {
  const [columnImage, setColumnImage] = useState([]);
  const { images, getImages, imagesFiltered } = useContext(imageContext);

  useEffect(() => {
    getImages();
    // eslint-disable-next-line
  }, []);
  useEffect(() => {
    if (images) {
      if (imagesFiltered) {
        setColumnImage(
          imagesFiltered.length === 0
            ? imagesFiltered
            : chunkArray(imagesFiltered, 3)
        );
      } else {
        setColumnImage(images.length === 0 ? images : chunkArray(images, 3));
      }
    }
    // eslint-disable-next-line
  }, [images, imagesFiltered]);
  return (
    <Fragment>
      <Layout />
      <Container isEmpty={columnImage.length === 0}>
        {columnImage.map((columnImage, i) => (
          <Column key={i}>
            {columnImage.map((image) => (
              <ImageItem imgData={image} key={image.id} />
            ))}
          </Column>
        ))}
        {columnImage.length === 0 && images && !imagesFiltered && (
          <h1>Add images to your gallery</h1>
        )}
        {columnImage.length === 0 && imagesFiltered && <h1>Without results</h1>}
      </Container>
    </Fragment>
  );
};

const Container = styled.div`
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
  ${({ isEmpty }) =>
    isEmpty && { justifyContent: "center", alignItems: "center" }}
`;

const Column = styled.div`
  -ms-flex: calc(100% / 3); /* IE10 */
  flex: calc(100% / 3);
  max-width: calc(100% / 3);
  padding: 0 4px;
`;

export default Images;
