package com.xiaobu.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/16 10:05
 * @description V1.0每个类至少要有一个构造函数，
 * 如果你自己构建了一个带有参数的构造函数而没有再显示的写出无参的构造函数也是可以的，
 * 不过当你尝试通过一个无参的构造函数来构建（new）时，此时编译器才会报错，
 * 因为找不到这个无参的构造函数。也就是说当一个类你没有给他构造函数，
 * 则编译器会自动补上一个无参的，若有的话就不会，你需要显示将此无参的构造函数写出来。
 */
@Getter
@Setter
@ToString
public class BookVo implements Serializable {
    private static final long serialVersionUID = 294655766971777057L;

    public BookVo(){}

    public BookVo(String name, String author) {
        super();
        this.name = name;
        this.author = author;
    }

    private String name;

    private String author;
}
