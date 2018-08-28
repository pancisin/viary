const baseUrl = () => {
  const url = window.location;
  return `${url.protocol}//${url.host}/${url.pathname.split('/')[1]}`;
};

export { baseUrl };
