package base

import (
	"errors"
	"fmt"
)

func sum(a, b int) int {
	return a + b
}

// 多值返回,必须要用小括号括起来
func sum1(a, b int) (int, error) {
	if a < 0 || b < 0 {
		return 0, errors.New("a b 不能为负数")
	}
	return a + b, nil
}

// 忽略返回值
func test() {
	var testV int
	testV, _ = sum1(1, 2)
	fmt.Printf(string(rune(testV)))
}

// 返回值也可以命名
func sum2(a, b int) (sum int, err error) {
	if a < 0 || b < 0 {
		return 0, errors.New("a b 不能为负数")
	}
	sum = a + b
	err = nil
	return
}

// 可变参数
// 如果既有普通参数，也有可变参数，那么可变参数要放在最后
func sum3(params ...int) int {
	sum := 0
	for _, i := range params {
		sum += i
	}
	return sum
}
func test1() {
	// 匿名函数
	// sum2只是一个函数类型的变量，不是一个普通的函数
	sum2 := func(a, b int) int {
		return a + b
	}
	fmt.Println(sum2(2, 3))
}

// 方法必须有一个接收者，这个接收者是一个类型
// 这样方法就和这个类型绑定在一起，叫做这个类型的方法
type Age uint

func (age Age) String() {
	fmt.Println("The age is ", age)
}
func test2() {
	age := Age(5)
	age.String()

	// 方法也可以赋值给某个变量，但是在使用的时候，要传一个接收者，也就是第一个参数
	sm := Age.String
	sm(age)
}

type person struct {
	name string
	age  int
}

func test3() {
	//var p person
	//有先后顺序
	p := person{"张正娇", 33}
	p1 := person{age: 18, name: "张鹏"}
	fmt.Println(p, p1)
}

// 接口
type Stringer interface {
	String() string
	xxx() int
}

func (p person) String() string {
	return fmt.Sprintf("Name is %s, age is %d", p.name, p.age)
}

func printStringer(s fmt.Stringer) {
	fmt.Println(s.String())
}
func test4() {
	p := person{"张正娇", 33}
	printStringer(p)

	var a, ok = p.(person)
	fmt.Println(a, ok)
}
