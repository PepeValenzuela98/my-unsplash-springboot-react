import React from "react";
import { Global, css } from "@emotion/core";

const GlobalStyles = () => {
  return (
    <Global
      styles={css`
        :root {
          font-family: "Noto Sans", sans-serif;
          font-weight: 400;
          input {
            outline: none !important;
          }
          button {
            outline: none !important;
          }
        }

        * {
          margin: 0;
          padding: 0;
          box-sizing: border-box;
        }

        html,
        body {
          margin: 0;
          height: 100vh;
          padding: 0px;
          margin: 0px;
        }
      `}
    />
  );
};

export default GlobalStyles;
