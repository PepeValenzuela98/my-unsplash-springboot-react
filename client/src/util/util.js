export function uuid() {
  return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (c) {
    var r = (Math.random() * 16) | 0,
      v = c === "x" ? r : (r & 0x3) | 0x8;
    return v.toString(16);
  });
}

export function chunkArray(array, size) {
  let newArray = [];
  let countSize = 0;
  let newItem = [];
  array.forEach((item, i) => {
    countSize++;

    if (countSize > size) {
      newArray = [...newArray, newItem];
      newItem = [];
      newItem = [...newItem, item];
      countSize = 1;
    } else {
      newItem = [...newItem, item];
    }
    if (i === array.length - 1 && newItem.length <= 3) {
      newArray = [...newArray, newItem];
      newItem = [];
      countSize = 0;
    }
  });

  newArray = orderArray(newArray, size);

  return newArray;
}

function orderArray(array, orderNumber) {
  let newArray = range(orderNumber);
  newArray.forEach((arr, i) => {
    array.forEach((item, j) => {
      item.forEach((itemArr, k) => {
        if (i === k) {
          arr = [...arr, itemArr];
        }
      });
    });
    newArray[i] = arr;
  });

  return newArray;
}

function range(size, startAt = 0) {
  return [...Array(size).keys()].map((i) => []);
}
