import React from "react";
import "../styles/InputComponent.css";

export const InputComponent = ({ value, onChange, placeholder, type }) => {
    return (
        <input
            className="input"
            type={type || "text"}
            value={value}
            onChange={onChange}
            placeholder={placeholder || ""}
        />
    );
};