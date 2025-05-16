import { useForm } from "react-hook-form";
import React, { useState } from "react";
import useAuth from "./useAuth";
import { useAuthContext } from "./AuthContext";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGoogle, faFacebook } from "@fortawesome/free-brands-svg-icons";
export default function SignIn() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const { logInUser, loading, error, success } = useAuth();

  const onSubmit = (data) => {
    const { email, password } = data;
    logInUser({ email, password });
  };

  const { googleLogin, facebookLogin } = useAuthContext();

  return (
    <div
      className="form-wrapper"
      style={{
        minHeight: "100vh",
      }}
    >
      <h1>Sign in</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <input
          type="email"
          placeholder="email"
          {...register("email", { required: "Email is required" })}
        />
        <input
          type="password"
          placeholder="password"
          {...register("password", { required: "Password is required" })}
        />

        {errors.email && <p>{errors.email.message}</p>}
        {!errors.email && errors.password && <p>{errors.password.message}</p>}

        {loading && <p style={{ color: "orange" }}>Wait...</p>}
        {error ? (
          <p>{error}</p>
        ) : success ? (
          <p style={{ color: "green" }}>Success!</p>
        ) : (
          ""
        )}
        <button type="submit">Sign up</button>
      </form>
      <div style={{ width: "300px", margin: "0 auto" }}>
        <button
          className="bg-red-500 text-white px-4 py-2 rounded"
          style={{width:"100%"}}
          onClick={()=>{googleLogin()}}
        >
          <FontAwesomeIcon icon={faGoogle} style={{ padding: "0 1rem" }} />
          Zaloguj przez Google
        </button>
      </div>
      <div style={{ width: "300px", margin: "1rem auto" }}>
        <button
          className="bg-red-500 text-white px-4 py-2 rounded"
          style={{width:"100%"}}
          onClick={()=>{facebookLogin()}}
        >
          <FontAwesomeIcon icon={faFacebook} style={{ padding: "0 1rem" }} />
         Zaloguj przez Facebook
        </button>
      </div>
    </div>
  );
}
