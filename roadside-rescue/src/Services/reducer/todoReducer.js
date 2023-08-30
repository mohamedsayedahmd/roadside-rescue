const intalValue = { todos: [] };

export const todoReducer = (state = intalValue, action) => {
  switch (action.type) {
    case "ADD_TODO":
      return { todos: action.payload };

    case "REMOVE_TODO":
      return { todos: action.payload };

    default:
      return state;
  }
}; //(1)
