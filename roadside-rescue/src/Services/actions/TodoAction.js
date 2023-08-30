export const actionAddTodo = (todo) => {
  return async (dispatch, getState) => {
    const {
      todoRed: { todos },
    } = getState(); //get access
    // const hasTodo = todos.find((item) => item.todo === todo);

    // const hasTodo = todos.find((item) => item.todo === todo);

    if (todo !== "") {
      dispatch({
        type: "ADD_TODO",
        payload: [{ id: todo, todo }, ...todos],
      });
    }
  };
};

export const actionRemoveTodo = (todo) => {
  return async (dispatch, getState) => {
    const {
      todoRed: { todos },
    } = getState();
    dispatch({
      type: "REMOVE_TODO",
      payload: todos.filter((t) => t.id !== todo.id),
    });
  };
};
