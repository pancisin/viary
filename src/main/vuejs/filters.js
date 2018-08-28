import { DateTime } from 'luxon';

const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
const bytes = value => {
  if (value === 0) return '0 Byte';

  const i = parseInt(Math.floor(Math.log(value) / Math.log(1024)), 10);
  return Math.round(value / Math.pow(1024, i), 2) + ' ' + sizes[i];
};

const luxon = (value, arg) => {
  return DateTime.fromMillis(value, {
    zone: 'utc'
  }).toFormat(arg);
};

export {
  bytes,
  luxon
};
