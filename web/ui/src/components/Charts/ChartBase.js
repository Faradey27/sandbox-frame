/*eslint-disable*/;
import React from 'react';
import nv from 'nvd3';
import d3 from 'd3';

import chartData from './../../../mocks/multiAreaChart.json';

class ChartBase extends React.Component {
  componentDidMount() {
    nv.addGraph(() => {
      this.chart = nv.models.stackedAreaChart()
        .margin({right: 100})
        .x((d) => d[0])
        .y((d) => d[1])
        .useInteractiveGuideline(true)
        .rightAlignYAxis(true)
        .showControls(true)
        .clipEdge(true);
      this.chart.xAxis.tickFormat((d) => d3.time.format('%x')(new Date(d)));
      this.chart.yAxis.tickFormat(d3.format(',.2f'));
      d3.select(this.svgNode).datum(chartData).call(this.chart);
      nv.utils.windowResize(this.chart.update);
      return this.chart;
    });
  }

  render() {
    return (
        <div className="chart-base">
          <svg ref={(node) => { this.svgNode = node }}/>
        </div>
      );
  }
};

export default ChartBase;
