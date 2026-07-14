<script lang="ts">
	import type { Nation } from "$lib/api/types/nations.d.ts";
	import Icon from "@iconify/svelte";

    let {
        nation,
        onclick,
    }: {
        nation: Nation,
        onclick?: (e: MouseEvent) => void;
    } = $props();

    let hover: boolean = $state(false);

</script>

<!-- svelte-ignore a11y_no_static_element_interactions -->
<!-- svelte-ignore a11y_click_events_have_key_events -->
<div 
    onclick={onclick}
    onmouseenter={() => {hover = true}} 
    onmouseleave={() => {hover = false}} 
    class="flex relative flex-row rounded-md transition-colors duration-200 bg-panel hover:bg-panel-hover active:bg-panel-active hover:cursor-pointer"
>
    <span class="text-center basis-1/3">{nation.longName}</span>
    <span class="text-center basis-1/3">{nation.shortName}</span>
    <span class="text-center basis-1/3">{nation.flagEmoji}</span>
    {#if hover}
        <Icon class="absolute right-2" icon="material-symbols:edit" />
    {/if}
</div>
