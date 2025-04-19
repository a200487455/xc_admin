axios.post('/api/register', this.registerForm, {
  headers: {
    'Content-Type': 'application/json'
  }
})
.then(response => {
  this.$message.success(response.data);
  this.$router.push('/login');
})
.catch(error => {
  const msg = (error.response && error.response.data) || '注册失败';
  this.$message.error(msg);
});


export default {
  name: 'Register',
  data() {
    return {
      registerForm: {
        username: '',
        password: '',
        email: '',
        phone: ''
      },
      loading: false,
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }],
        phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }]
      }
    };
  },
  methods: {
    handleRegister() {
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          // 使用 qs.stringify 格式化数据
          axios.post('/api/register', qs.stringify(this.registerForm))
            .then((response) => {
              this.$message.success(response.data);
              this.$router.push('/login');
            })
            .catch((error) => {
              const msg = (error.response && error.response.data) || '注册失败';
              this.$message.error(msg);
            })
            .finally(() => {
              this.loading = false;
            });
        }
      });
    }
  }
};



<template>
  <div class="login-container">
    <el-form ref="registerForm" :model="registerForm" :rules="rules" class="login-form" auto-complete="on" label-position="left">
      <div class="title-container">
        <h3 class="title">注册</h3>
      </div>

      <el-form-item prop="username">
        <el-input v-model="registerForm.username" placeholder="用户名" />
      </el-form-item>

      <el-form-item prop="password">
        <el-input v-model="registerForm.password" type="password" placeholder="密码" show-password />
      </el-form-item>

      <el-form-item prop="email">
        <el-input v-model="registerForm.email" placeholder="邮箱" />
      </el-form-item>

      <el-form-item prop="phone">
        <el-input v-model="registerForm.phone" placeholder="手机号" />
      </el-form-item>

      <el-button :loading="loading" type="primary" style="width: 100%" @click="handleRegister">
        注册
      </el-button>

      <div style="margin-top: 20px; text-align: right;">
        <router-link to="/login">已有账号？登录</router-link>
      </div>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Register',
  data() {
    return {
      registerForm: {
        username: '',
        password: '',
        email: '',
        phone: ''
      },
      loading: false,
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }],
        phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }]
      }
    };
  },
  methods: {
    handleRegister() {
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          axios.post('/api/register', this.registerForm)
            .then(() => {
              this.$message.success('注册成功');
              this.$router.push('/login');
            })
            .catch((error) => {
              const msg =
                (error.response &&
                 error.response.data &&
                 error.response.data.message) || '注册失败';
              this.$message.error(msg);
            })
            .finally(() => {
              this.loading = false;
            });
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/styles/login.scss";
</style>
