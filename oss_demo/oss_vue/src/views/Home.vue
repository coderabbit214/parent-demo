<template>
  <div class="home" style="height: 100%">
    <el-upload
        :action="123"
        :data="dataObj"
        :http-request="upload"
        list-type="picture"
        :multiple="false"
        :file-list="fileList"
    >
      <el-button size="small" type="primary">点击上传</el-button>
    </el-upload>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Home',
  props: {
    value: String
  },
  data() {
    return {
      fileList: [],
    }
  },
  methods: {
    //向后端请求上传签名
    upload(item) {
      //请求参数
      let data;
      //文件
      let file = item.file;

      axios.get("http://127.0.0.1:8081/getPolicy?fileName=" + file.name).then(res => {
        res = res.data;
        //后端传递的请求参数
        let formData = res.formData;
        //文件oss上的根路径+文件名
        let key = formData.key;
        //判断是否需要formData
        if (res.withFormData) {
          data = new FormData();
          for (let param in formData) {
            data.append(param, formData[param])
          }
          data.append(res.fileFieldName, file);
        } else {
          data = file;
        }
        //存储
        axios({
          url: res.host,
          data: data,
          method: res.method,
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }).then(res => {
          //向后端存储（伪代码）：
          /**
           * save({
           *  ...
           *   url: this.dataObj.host + '/' + this.dataObj.key
           *  ...
           * })
           */
          //前端使用：
          axios.get("http://127.0.0.1:8081/getExpiration?objectName=" + key).then(res => {
            this.fileList.push({
              name: file.name,
              url: res.data
            })
          })
        })
      });

    },
  }
}
</script>
