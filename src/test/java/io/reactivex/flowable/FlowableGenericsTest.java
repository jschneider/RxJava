/**
 * Copyright (c) 2016-present, RxJava Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package io.reactivex.flowable;

import io.reactivex.*;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
public class FlowableGenericsTest {
    @Test
    public void basics() throws Exception {
        Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4);

        BiFunction<Integer, Integer, Integer> sumReducer = new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        };

        Maybe<Integer> reduced = flowable.reduce(sumReducer);

        reduced.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                assertEquals(10, integer, 0);
            }
        });
    }

    @Test
    public void reduceWithSuperType() throws Exception {
        Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4);

        BiFunction<Number, Number, Integer> sumReducer = new BiFunction<Number, Number, Integer>() {
            @Override
            public Integer apply(Number integer, Number integer2) throws Exception {
                int value = integer.intValue() + integer2.intValue();
                return value;
            }
        };

        Maybe<Integer> reduced = flowable.reduce(sumReducer);
        reduced.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                assertEquals(10, integer, 0);
            }
        });
    }

    @Test
    public void reduceWithSuperTypeNumberFlowable() throws Exception {
        Flowable<Number> flowable = Flowable.<Number>just(1, 2, 3, 4);

        BiFunction<Number, Number, Integer> sumReducer = new BiFunction<Number, Number, Integer>() {
            @Override
            public Integer apply(Number integer, Number integer2) throws Exception {
                int value = integer.intValue() + integer2.intValue();
                return value;
            }
        };

        Maybe<Number> reduced = flowable.reduce(sumReducer);
        reduced.subscribe(new Consumer<Number>() {
            @Override
            public void accept(Number integer) throws Exception {
                assertEquals(10, integer.doubleValue(), 0);
            }
        });
    }

    @Test
    public void reduceWithSuperTypeNumberFlowable2() throws Exception {
        Flowable<Number> flowable = Flowable.<Number>just(1, 2, 3, 4);

        BiFunction<Number, Number, Number> sumReducer = new BiFunction<Number, Number, Number>() {
            @Override
            public Number apply(Number integer, Number integer2) throws Exception {
                int value = integer.intValue() + integer2.intValue();
                return value;
            }
        };

        Maybe<Number> reduced = flowable.reduce(sumReducer);
        reduced.subscribe(new Consumer<Number>() {
            @Override
            public void accept(Number integer) throws Exception {
                assertEquals(10, integer.doubleValue(), 0);
            }
        });
    }
}
