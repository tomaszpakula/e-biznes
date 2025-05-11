import { useForm } from "react-hook-form";
import React, { useState } from "react";
import useAuth from "./useAuth";
import { useAuthContext } from "./AuthContext";

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

  return (
    <div
      className="form-wrapper"
      style={{
        height: "100vh",
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
        {!errors.email && errors.password && (
          <p>{errors.password.message}</p>
        )}
  
        {loading && <p style={{color: "orange"}}>Wait...</p>}
        {error ? <p>{error}</p> :success ? <p style={{color:"green"}}>Success!</p>:""}
        <button type="submit">Sign up</button>
      </form>
    </div>
  );
}
