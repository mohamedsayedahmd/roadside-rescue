export const setEmailAction = (email) => ({
  type: "SET_EMAIL",
  payload: email,
});

export const setPasswordAction = (password) => ({
  type: "SET_PASSWORD",
  payload: password,
});

export const clearEmailAction = () => ({
  type: "CLEAR_EAMIL",
  payload: "",
});
export const clearPasswordAction = () => ({
  type: "CLEAR_PASSWORD",
  payload: "",
});
