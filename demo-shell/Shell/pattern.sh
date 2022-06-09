# (?!exp) 匹配后面跟的不是exp的位置
# 不匹配test
^(?!test+$)

# 不以test开头
^(?!test)
^(?!test).*$

# 不能全为数字
^(?![0-9]+$)

# 字母数字开头，字母数字结尾，可以有 "-" 符号
[a-z0-9]([-a-z0-9]*[a-z0-9])?