import { useForm } from "react-hook-form";
import React, { useState } from "react";
import useAuth from "./useAuth";

export default function SignUp() {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const { registerUser, loading, error, success } = useAuth();

  const onSubmit = (data) => {
    const { username, email, password } = data;
    registerUser({ username, email, password });
  };

  const password = watch("password");

  return (
    <div
      className="form-wrapper"
      style={{
        height: "100vh",
      }}
    >
      <h1>Sign up</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <input
          type="text"
          placeholder="username"
          {...register("username", { required: "Username is required" })}
        />
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
        <input
          type="password"
          placeholder="repeat password"
          {...register("password2", {
            required: "Repeat the password",
            validate: {
              arePasswordsTheSame: (password2) => {
                return password === password2 || "Passwords are not the same";
              },
            },
          })}
        />
        {errors.username && <p>{errors.username.message}</p>}
        {!errors.username && errors.email && <p>{errors.email.message}</p>}
        {!errors.username && !errors.email && errors.password && (
          <p>{errors.password.message}</p>
        )}
        {!errors.username &&
          !errors.email &&
          !errors.password &&
          errors.password2 && <p>{errors.password2.message}</p>}
        {loading && <p style={{color: "orange"}}>Wait...</p>}
        {error && <p>{error}</p>}
        {success && <p style={{color:"green"}}>Success!</p>}
        <button type="submit">Sign up</button>
      </form>
    </div>
  );
}
