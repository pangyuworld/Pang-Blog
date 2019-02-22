<script>
// const adress = "http://localhost:8877";
const adress = ADDRESS;
function isEmpty(obj) {
  if (typeof obj == "undefined" || obj == null || obj == "") {
    return true;
  } else {
    return false;
  }
}
function showText(str) {
  if (str.length > 40) {
    return str.substring(0, 40) + "...";
  } else {
    return str;
  }
}
export default {
  adress,
  isEmpty,
  showText
};
</script>