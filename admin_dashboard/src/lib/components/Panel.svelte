<script lang="ts">
	import type { Snippet } from "svelte";

    let {
        class: className,
        children
    }: {
        class?: string,
        children: Snippet
    } = $props();
</script>

<div class={`${className} p-2 bg-panel rounded-md`}>
    {@render children()}
</div>
