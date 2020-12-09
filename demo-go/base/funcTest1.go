package base

import (
	"errors"
	"fmt"
	"strconv"
)

type WalkRun interface {
	Walk()
	Run()
}

func (p *person) Walk() {
	fmt.Println("可以走")
}

func (p *person) Run() {
	fmt.Println("可以跑")
}

// 错误通过内置的error接口表示
type error interface {
	Error() string
}

func test5() {
	i, err := strconv.Atoi("a")
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(i)
	}
}

type commonError struct {
	errorCode int
	errMsg    string
}

func (ce *commonError) Error() string {
	return ce.errMsg
}

func test6() (int, *commonError) {
	return 0, &commonError{
		errorCode: 404,
		errMsg:    "Not found",
	}
}

func test7() {
	sum, err := sum2(-1, 2)
	if cm, ok := err.(*commonError); ok {
		fmt.Println(cm.errorCode, cm.errMsg)
	} else {
		fmt.Println(sum)
	}
}

func test8() {
	e := errors.New("原始错误")
	w := fmt.Errorf("Error里面有Error:%w", e)
	fmt.Println(w)
	// 还可以把嵌套的Error打开
	fmt.Println(errors.Unwrap(w))
}

// deffer
// panic异常，是一种非常严重的异常，会导致程序崩溃，所以在一般情况下，不要使用panic
// recover可以捕获panic异常
// 比较提倡error