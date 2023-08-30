const intalValue = {
  email: "",
  password: "",
};

export const loginReducer = (state = intalValue, action) => {
  switch (action.type) {
    case "SET_EMAIL":
      return {
        ...state,
        email: action.payload,
      };
    case "SET_PASSWORD":
      return {
        ...state,
        password: action.payload,
      };
    case "CLEAR_EAMIL":
      return {
        ...state,
        email: action.payload,
      };
    case "CLEAR_PASSWORD":
      return {
        ...state,
        password: action.payload,
      };
    default:
      return state;
  }
};
