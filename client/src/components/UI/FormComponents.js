import React from "react";
import { ErrorMessage } from "./FormStyledComponents";

export function ErrorField({ condition, message }) {
  if (condition) {
    return null;
  }
  return <ErrorMessage>{message}</ErrorMessage>;
}
