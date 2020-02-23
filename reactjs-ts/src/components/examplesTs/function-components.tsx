import React from 'react';

interface CountProp {
    count: number;
}

const Count: React.FunctionComponent<CountProp> = (props: CountProp) => {
    return <h1>{props.count}</h1>;
};

export default Count;